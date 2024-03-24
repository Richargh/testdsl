package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import de.richargh.sampleusage.app.SmallAppFloor;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Entity;
import de.richargh.sampleusage.kernel.EntityId;
import de.richargh.sampleusage.kernel.Repository;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTestStateTest {

    @ParameterizedTest(name = "Should store a {0} in the repository when we make and then save to the floor")
    @ArgumentsSource(Entities.class)
    void should_store_teststate_in_floor(
            String ignoredTestName,
            Function<AppTestState, Entity<EntityId>> makeEntity,
            Function<AppFloor, Repository<EntityId, Entity<EntityId>>> extractRepo) {
        // given
        var floor = new SmallAppFloor();
        var testState = new AppTestState(floor.clock(), DataFabrications.create(), floor.ids());
        // when
        var expected = makeEntity.apply(testState);
        testState.saveTo(floor);
        // then
        var repo = extractRepo.apply(floor);
        var actual = repo.findById(expected.id());
        assertThat(actual).contains(expected);
    }

    private static class Entities implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arg("Book", AppTestState::book, AppFloor::books),
                    arg("Users", AppTestState::user, AppFloor::users),
                    arg("Roles", AppTestState::role, AppFloor::roles),
                    arg("Permission", AppTestState::permission, AppFloor::permissions)
            );
        }

        private static <TId extends EntityId, TEntity extends Entity<TId>> Arguments arg(
                String testName,
                Function<AppTestState, TEntity> makeEntity,
                Function<AppFloor, Repository<TId, TEntity>> extractRepo) {
            return Arguments.of(testName, makeEntity, extractRepo);
        }

    }
}

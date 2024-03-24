package de.richargh.sampleusage.auth;

import de.richargh.testdslcontrol.AppTestState;
import de.richargh.testdslcontrol.Small;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class UsersContract {
    abstract Users testee();

    @Small
    @Test
    void should_remember_permission(AppTestState a) {
        // given
        var user = a.user();
        var testee = testee();
        // when
        testee.add(user);
        // then
        assertThat(testee.findById(user.id())).contains(user);
    }
}

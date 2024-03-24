package de.richargh.sampleusage.kernel;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataFabricationsTest {

    @Nested
    class NameFabrications {

        @Test
        void should_generate_string_names_from_backlog() {
            // given
            var nameBacklog = List.of("Alex", "Taylor");
            var testee = DataFabrications.create();
            // when
            var result1 = testee.namesOf(getClass(), nameBacklog).next();
            var result2 = testee.namesOf(getClass(), nameBacklog).next();
            // then
            assertThat(result1).isEqualTo("Alex");
            assertThat(result2).isEqualTo("Taylor");
        }

        @Test
        void should_generate_random_name_without_backlog() {
            // given
            var testee = DataFabrications.create();
            // when
            var result1 = testee.namesOf(getClass(), Collections.emptyList()).next();
            var result2 = testee.namesOf(getClass(), Collections.emptyList()).next();
            // then
            assertThat(result1).isNotNull();
            assertThat(result2).isNotNull();
            assertThat(result1).isNotEqualTo(result2);
        }

        @Test
        void should_fallback_to_random_names_when_backlog_used_up() {
            // given
            var nameBacklog = List.of("Alex");
            var testee = DataFabrications.create();
            // when
            var result1 = testee.namesOf(getClass(), nameBacklog).next();
            var result2 = testee.namesOf(getClass(), nameBacklog).next();
            // then
            assertThat(result1).isEqualTo("Alex");
            assertThat(result2).isNotNull();
            assertThat(result1).isNotEqualTo(result2);
        }
    }
}
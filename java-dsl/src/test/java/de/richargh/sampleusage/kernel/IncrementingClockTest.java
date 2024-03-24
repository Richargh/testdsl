package de.richargh.sampleusage.kernel;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoField;

import static org.assertj.core.api.Assertions.assertThat;

class IncrementingClockTest {
    @Test
    void should_return_provided_now_on_first_call() {
        // given
        var expected = Instant.now().with(ChronoField.NANO_OF_SECOND, 0);
        var testee = new IncrementingClock(expected);
        // when
        var actual = testee.now();
        // then
        assertThat(actual).isBeforeOrEqualTo(expected);
        assertThat(actual).isAfterOrEqualTo(expected);
    }

    @Test
    void should_increment_provided_now_on_every_call() {
        // given
        var testee = new IncrementingClock(Instant.now());
        // when
        var result1 = testee.now();
        var result2 = testee.now();
        var result3 = testee.now();
        // then
        assertThat(result2).isAfter(result1);
        assertThat(result3).isAfter(result2);
    }
}
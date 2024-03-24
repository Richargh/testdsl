package de.richargh.sampleusage.app;

import de.richargh.testdslcontrol.AppTestState;
import de.richargh.testdslcontrol.Small;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BooksContract {
    abstract Books testee(); // <2>

    @Small
    @Test
    void should_remember_book(AppTestState a) { // <3>
        // given
        var book = a.book();
        var testee = testee();
        // when
        testee.add(book);
        // then
        assertThat(testee.findById(book.id())).contains(book);
    }
}

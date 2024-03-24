package de.richargh.sampleusage.app;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.testdslcontrol.BaseTestBuilder;

import java.util.function.Consumer;

public final class BookBuilder extends BaseTestBuilder<Book> {

    public String name = nextNameOf("Refactoring", "Xp Explained");
    public BookStatus status = BookStatus.AVAILABLE;

    public BookBuilder(Clock clock, DataFabrications fabrications, Ids ids) {
        super(clock, fabrications, ids);
    }

    public Book build() {
        return new Book(
                ids.next(BookId.class),
                this.name,
                status,
                clock.now());
    }

    public BookBuilder with(Consumer<BookBuilder> action) {
        action.accept(this);
        return this;
    }

    public BookBuilder isRented() {
        status = BookStatus.RENTED;
        return this;
    }
}

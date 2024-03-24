package de.richargh.sampleusage.app;

public class InMemoryBooksTest extends BooksContract {
    @Override
    Books testee() {
        return new InMemoryBooksDouble();
    }
}

package de.richargh.sampleusage.app;

import de.richargh.sampleusage.auth.UserId;
import de.richargh.sampleusage.kernel.EntityNotFoundException;

public class RentingService {
    private final Books books;

    public RentingService(AppFloor floor) {
        this.books = floor.books();
    }

    public RentResult rentBook(BookId bookId, UserId userId) {
        var book = books.findById(bookId).orElseThrow(() -> new EntityNotFoundException(bookId, Book.class));
        var result = book.rent();
        books.add(result.book());
        return result;
    }
}

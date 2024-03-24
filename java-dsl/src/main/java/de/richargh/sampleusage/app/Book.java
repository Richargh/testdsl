package de.richargh.sampleusage.app;

import de.richargh.sampleusage.kernel.Entity;

import java.time.Instant;

public record Book(
        BookId id,
        String name,
        BookStatus status,
        Instant createdOn
) implements Entity<BookId> {
    public boolean isRented() {
        return status == BookStatus.RENTED;
    }

    public RentResult rent() {
        return switch (status) {
            case AVAILABLE -> new RentResult.RentedToUser(new Book(
                    id,
                    name,
                    BookStatus.RENTED,
                    createdOn
            ));
            case RENTED -> new RentResult.RentedToAnotherUser(this);
        };
    }
}

package de.richargh.sampleusage.app;

public sealed interface RentResult {

    Book book();

    record RentedToUser(Book book) implements RentResult {
    }

    record RentedToAnotherUser(Book book) implements RentResult {
    }
}

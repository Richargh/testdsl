package de.richargh.sampleusage.app;

import de.richargh.testdslcontrol.AppTestState;
import de.richargh.testdslcontrol.Small;
import org.junit.jupiter.api.Test;

import static de.richargh.sampleusage.auth.PermissionCode.CAN_RENT_BOOKS;
import static org.assertj.core.api.Assertions.assertThat;

@Small
public class RentingServiceTest {

    @Test
    void should_be_able_to_rent_book(AppTestState a, AppFloor floor) {
        // given
        var book = a.book();
        var userBundle = a.userCombo(it -> it.hasPermissions(CAN_RENT_BOOKS));
        a.saveTo(floor);

        var testee = new RentingService(floor);
        // when
        var result = testee.rentBook(book.id(), userBundle.user().id());
        // then
        assertThat(result).isInstanceOf(RentResult.RentedToUser.class);
    }

    @Test
    void should_be_able_to_store_rented_book(AppTestState a, AppFloor floor) {
        // given
        var book = a.book();
        var userBundle = a.userCombo(it -> it.hasPermissions(CAN_RENT_BOOKS));
        a.saveTo(floor);

        var testee = new RentingService(floor);
        // when
        testee.rentBook(book.id(), userBundle.user().id());
        // then
        var result = floor.books().findById(book.id()).orElseThrow();
        assertThat(result.isRented()).isTrue();
    }

    @Test
    void should_not_be_able_to_rent_rented_book(AppTestState a, AppFloor floor) {
        // given
        var book = a.book(BookBuilder::isRented);
        var userBundle = a.userCombo(it -> it.hasPermissions(CAN_RENT_BOOKS));
        a.saveTo(floor);

        var testee = new RentingService(floor);
        // when
        var result = testee.rentBook(book.id(), userBundle.user().id());
        // then
        assertThat(result).isInstanceOf(RentResult.RentedToAnotherUser.class);
    }

}

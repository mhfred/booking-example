package com.example.booking.model;

import com.example.booking.model.bookingRule.*;
import com.example.booking.model.exception.BookingException;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Created by deadlock on 11/2/17.
 */
@Builder
class BookingRules {
    @NotNull
    private List<BookingRule> bookingRuleList;

    void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        for (BookingRule bookingRule : bookingRuleList) {
            bookingRule.validate(reservationRequest, bookable);
        }
    }

    static BookingRules MEETING_ROOM_BOOKING_RULES() {
        List<BookingRule> bookingRuleList = Arrays.asList(
                new ValidReservationRequest(),
                new WeekdayOnly(),
                new MaxTwoHourOnly(),
                new NoOverlapForCreateRequest(),
                new NoOverlapForUpdateRequest()
        );
        return BookingRules.builder().bookingRuleList(bookingRuleList).build();
    }

    static BookingRules FOOSBALL_BOOKING_RULES() {
        List<BookingRule> bookingRuleList = Arrays.asList(
                new ValidReservationRequest(),
                new WeekendOnly(),
                new MaxOneHourOnly(),
                new NoOverlapForCreateRequest(),
                new NoOverlapForUpdateRequest()
        );
        return BookingRules.builder().bookingRuleList(bookingRuleList).build();
    }
}

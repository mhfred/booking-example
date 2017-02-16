package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.Reservation;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;
import com.example.booking.model.exception.BookingRuleViolationException;

import java.util.Calendar;

/**
 * Created by deadlock on 15/2/17.
 */
public class NoOverlapForCreateRequest implements BookingRule {

    @Override
    public void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        if (reservationRequest.getRequestType()== ReservationRequest.RequestType.CREATE &&
                !isAvailable(reservationRequest, bookable)) {
            throw new BookingRuleViolationException("requested time is not available.");
        }
    }

    boolean isAvailable(ReservationRequest request, Bookable bookable) throws BookingException{
        return true;
    }
}

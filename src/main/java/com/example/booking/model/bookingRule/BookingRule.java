package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;

/**
 * Created by deadlock on 15/2/17.
 */
public interface BookingRule {
    void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException;
}

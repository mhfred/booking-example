package com.example.booking.model;

import com.example.booking.model.exception.BookingException;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by deadlock on 8/2/17.
 */
@Builder
public class BookableImpl implements Bookable {
    private BookingItem bookingItem;
    private BookingCalendar bookingCalendar;
    private BookingRules bookingRules;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Integer getBookableId() {
        return null;
    }

    @Override
    public String getBookableName() {
        return null;
    }

    @Override
    public Reservation book(ReservationRequest reservationRequest) throws BookingException {
        return null;
    }

    @Override
    public Reservation updateReservation(ReservationRequest reservationRequest) throws BookingException {
        return null;
    }

    @Override
    public void deleteReservation(ReservationRequest reservationRequest) throws BookingException {

    }

    @Override
    public Reservation getReservation(ReservationRequest reservationRequest) throws BookingException {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() throws BookingException {
        return null;
    }

    @Override
    public boolean checkAvailability(ReservationRequest reservationRequest) {
        return false;
    }
}

package com.example.booking.model;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Created by deadlock on 7/2/17.
 */

public interface BookingCalendar {
    Reservation addReservation(Bookable bookable, Calendar begin, Calendar end);

    Optional<Reservation> getReservation(Bookable bookable, Long reservationId);

    List<Reservation> getAllReservations(Bookable bookable);

    Reservation updateReservation(Bookable bookable, Long reservationId, Calendar begin, Calendar end);

    Reservation deleteReservation(Bookable bookable, Long reservationId);

    Integer count(Bookable bookable);
}

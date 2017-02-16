package com.example.booking.model;

import com.example.booking.model.exception.BookingException;

import java.util.List;

/**
 * Created by deadlock on 7/2/17.
 */
public interface Bookable {
    Integer getBookableId();

    String getBookableName();

    Reservation book(ReservationRequest reservationRequest) throws BookingException;

    Reservation updateReservation(ReservationRequest reservationRequest) throws BookingException;

    void deleteReservation(ReservationRequest reservationRequest) throws BookingException;

    Reservation getReservation(ReservationRequest reservationRequest) throws BookingException;

    List<Reservation> getAllReservations() throws BookingException;

    boolean checkAvailability(ReservationRequest reservationRequest);
}

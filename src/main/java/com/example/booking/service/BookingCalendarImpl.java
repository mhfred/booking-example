package com.example.booking.service;

import com.example.booking.model.Bookable;
import com.example.booking.model.BookingCalendar;
import com.example.booking.model.Reservation;
import com.example.booking.repository.BookingReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Created by deadlock on 15/2/17.
 */
@Service
@AllArgsConstructor
public class BookingCalendarImpl implements BookingCalendar{
    private BookingReservationRepository bookingReservationRepository;

    @Override
    public Reservation addReservation(Bookable bookable, Calendar begin, Calendar end) {
        return null;
    }

    @Override
    public Optional<Reservation> getReservation(Bookable bookable, Long reservationId) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations(Bookable bookable) {
        return null;
    }

    @Override
    public Reservation updateReservation(Bookable bookable, Long reservationId, Calendar begin, Calendar end) {
        return null;
    }

    @Override
    public Reservation deleteReservation(Bookable bookable, Long reservationId) {
        return null;
    }

    @Override
    public Integer count(Bookable bookable) {
        return null;
    }
}

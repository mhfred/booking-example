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
        return bookable.getAllReservations().stream()
                .filter(reservation -> isOverlap(reservation, request) | isTheSame(reservation, request))
                .count() == 0;
    }

    private boolean isOverlap(Reservation reservation, ReservationRequest request) {
        return isInBetween(reservation.getBegin(), reservation.getEnd(), request.getBegin())
                | isInBetween(reservation.getBegin(), reservation.getEnd(), request.getEnd())
                | isInBetween(request.getBegin(), request.getEnd(), reservation.getBegin())
                | isInBetween(request.getBegin(), request.getEnd(), reservation.getEnd());
    }

    private boolean isInBetween(Calendar begin, Calendar end, Calendar calendar) {
        boolean isAfterBegin = begin.compareTo(calendar) < 0;
        boolean isBeforeEnd = calendar.compareTo(end) < 0;
        return isAfterBegin & isBeforeEnd;
    }

    private boolean isTheSame(Reservation reservation, ReservationRequest reservationCreateRequest) {
        return reservation.getBegin().compareTo(reservationCreateRequest.getBegin()) == 0
                & reservation.getEnd().compareTo(reservationCreateRequest.getEnd()) == 0;
    }
}

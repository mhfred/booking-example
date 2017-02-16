package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;
import com.example.booking.model.exception.InvalidReservationRequestException;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;

/**
 * Created by deadlock on 15/2/17.
 */
public class ValidReservationRequest implements BookingRule {
    private Clock clock;
    private static final Integer DAYS_IN_ADVANCE = 7;

    public ValidReservationRequest(Clock clock) {
        this.clock = clock;
    }

    public ValidReservationRequest() {
        this.clock = Clock.systemDefaultZone();
    }

    @Override
    public void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        ReservationRequest.RequestType type = reservationRequest.getRequestType();

        switch (type){
            case CREATE:
                beginAndEndAreNotNull(reservationRequest);
                beginIsBeforeEnd(reservationRequest);
                beginIsInFuture(reservationRequest);
                beginIsNotAfterDaysInAdvance(reservationRequest);
                timeSlotIsHalfHour(reservationRequest);
                break;
            case READ:
                reservationIdIsNotNull(reservationRequest);
                break;
            case UPDATE:
                reservationIdIsNotNull(reservationRequest);
                beginAndEndAreNotNull(reservationRequest);
                beginIsBeforeEnd(reservationRequest);
                beginIsInFuture(reservationRequest);
                beginIsNotAfterDaysInAdvance(reservationRequest);
                timeSlotIsHalfHour(reservationRequest);
                break;
            case DELETE:
                reservationIdIsNotNull(reservationRequest);
                break;
        }
    }

    private void reservationIdIsNotNull(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        if(reservationRequest.getReservationId() == null){
            throw new InvalidReservationRequestException(("reservation request id is null"));
        }
    }

    private void beginAndEndAreNotNull(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        if(reservationRequest.getBegin() == null || reservationRequest.getEnd() == null){
            throw new InvalidReservationRequestException(("reservation request fields are null"));
        }
    }

    private void beginIsBeforeEnd(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        if(!reservationRequest.getBegin().before(reservationRequest.getEnd())){
            throw new InvalidReservationRequestException("reservation request begin is not before end");
        }
    }

    private void beginIsInFuture(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(Instant.now(clock).toEpochMilli());

        if(!now.before(reservationRequest.getBegin())){
            throw new InvalidReservationRequestException("reservation request begin is not in future");
        }
    }

    private void beginIsNotAfterDaysInAdvance(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        Calendar daysLater = Calendar.getInstance();
        daysLater.setTimeInMillis(Instant.now(clock).toEpochMilli());
        daysLater.add(Calendar.DATE, DAYS_IN_ADVANCE);

        if(daysLater.before(reservationRequest.getBegin())){
            throw new InvalidReservationRequestException("reservation request begin is not after "+DAYS_IN_ADVANCE+" days in advance");
        }
    }

    private void timeSlotIsHalfHour(ReservationRequest reservationRequest) throws InvalidReservationRequestException {
        if(!granularityIsHalfHour(reservationRequest.getBegin())
                || !granularityIsHalfHour(reservationRequest.getEnd())){
            throw new InvalidReservationRequestException("reservation request time slot is not half hour");
        }
    }

    private boolean granularityIsHalfHour(Calendar calendar) {
        return calendar.getTimeInMillis() % 1800 == 0;
    }
}

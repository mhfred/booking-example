package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;
import com.example.booking.model.exception.InvalidReservationRequestException;

import java.util.Calendar;

/**
 * Created by deadlock on 15/2/17.
 */
public class WeekendOnly implements BookingRule {

    @Override
    public void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        if(!isWeekend(reservationRequest.getBegin()) ){
            throw new InvalidReservationRequestException("reservation begin date is not weekend");
        }else if(!isWeekend(reservationRequest.getEnd())){
            throw new InvalidReservationRequestException("reservation end date is not weekend");
        }
    }

    private boolean isWeekend(Calendar calendar){
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
    }
}

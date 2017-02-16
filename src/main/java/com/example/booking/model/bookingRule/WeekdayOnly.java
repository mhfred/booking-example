package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;
import com.example.booking.model.exception.InvalidReservationRequestException;

import java.util.Calendar;

/**
 * Created by deadlock on 15/2/17.
 */
public class WeekdayOnly implements BookingRule {

    @Override
    public void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        if(!isWeekday(reservationRequest.getBegin())){
            throw new InvalidReservationRequestException("reservation request begin is not weekday");
        }else if(!isWeekday(reservationRequest.getEnd())){
            throw new InvalidReservationRequestException("reservation request end is not weekday");
        }
    }

    private boolean isWeekday(Calendar calendar){
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY;
    }
}

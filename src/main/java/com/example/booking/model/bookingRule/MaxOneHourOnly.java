package com.example.booking.model.bookingRule;

import com.example.booking.model.Bookable;
import com.example.booking.model.ReservationRequest;
import com.example.booking.model.exception.BookingException;
import com.example.booking.model.exception.BookingRuleViolationException;

import java.util.Calendar;


/**
 * Created by deadlock on 15/2/17.
 */
public class MaxOneHourOnly implements BookingRule {

    @Override
    public void validate(ReservationRequest reservationRequest, Bookable bookable) throws BookingException {
        Calendar begin = reservationRequest.getBegin();
        Calendar end = reservationRequest.getEnd();

        long duration = Math.abs(begin.getTimeInMillis() - end.getTimeInMillis());
        boolean greaterThanTwoHour = duration - 1000*3600 > 0;

        if(greaterThanTwoHour){
            throw new BookingRuleViolationException("request duration is greater than one hours");
        }
    }
}

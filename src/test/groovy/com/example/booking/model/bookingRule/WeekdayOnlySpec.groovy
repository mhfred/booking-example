package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.ReservationRequest
import com.example.booking.model.exception.BookingException
import com.example.booking.model.exception.BookingRuleViolationException
import com.example.booking.model.exception.InvalidReservationRequestException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


/**
 * Created by deadlock on 15/2/17.
 */
class WeekdayOnlySpec extends Specification {
    @Subject
    WeekdayOnly weekdayOnly = new WeekdayOnly()

    ReservationRequest reservationRequest
    Bookable bookable


    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() is valid weekday'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)

        when:
        weekdayOnly.validate(reservationRequest, bookable)


        then:
        notThrown(BookingException)

        where:
        begin | end
        new GregorianCalendar(2017,0,30,11,00,00) | new GregorianCalendar(2017,1,1,12,00,00)
        new GregorianCalendar(2017,0,31,11,00,00) | new GregorianCalendar(2017,1,2,12,00,00)
        new GregorianCalendar(2017,0,31,11,00,00) | new GregorianCalendar(2017,1,3,12,00,00)
    }

    @Unroll
    'begin: #begin.getTime(), end: #end.getTime() is not a valid weekday'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)


        when:
        weekdayOnly.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin | end
        new GregorianCalendar(2017,0,31,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,31,11,00,00) | new GregorianCalendar(2017,1,5,12,00,00)
        new GregorianCalendar(2017,0,29,11,00,00) | new GregorianCalendar(2017,1,3,12,00,00)
        new GregorianCalendar(2017,0,28,11,00,00) | new GregorianCalendar(2017,1,3,12,00,00)
        new GregorianCalendar(2017,0,28,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
    }
}
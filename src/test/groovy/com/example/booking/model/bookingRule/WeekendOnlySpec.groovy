package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.BookingCalendar
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
class WeekendOnlySpec extends Specification {
    @Subject
    WeekendOnly weekendOnly = new WeekendOnly()

    ReservationRequest reservationRequest
    Bookable bookable


    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() is valid weekend'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)

        when:
        weekendOnly.validate(reservationRequest, bookable)


        then:
        notThrown(BookingException)

        where:
        begin | end
        new GregorianCalendar(2017,0,21,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,22,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,22,11,00,00) | new GregorianCalendar(2017,1,5,12,00,00)
    }

    @Unroll
    'begin: #begin.getTime(), end: #end.getTime() is not a valid weekend'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)


        when:
        weekendOnly.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin | end
        new GregorianCalendar(2017,0,23,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,24,11,00,00) | new GregorianCalendar(2017,1,5,12,00,00)
        new GregorianCalendar(2017,0,25,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,26,11,00,00) | new GregorianCalendar(2017,1,5,12,00,00)
        new GregorianCalendar(2017,0,27,11,00,00) | new GregorianCalendar(2017,1,4,12,00,00)
        new GregorianCalendar(2017,0,28,11,00,00) | new GregorianCalendar(2017,1,6,12,00,00)
        new GregorianCalendar(2017,0,29,11,00,00) | new GregorianCalendar(2017,1,7,12,00,00)
        new GregorianCalendar(2017,0,29,11,00,00) | new GregorianCalendar(2017,1,8,12,00,00)
        new GregorianCalendar(2017,0,29,11,00,00) | new GregorianCalendar(2017,1,9,12,00,00)
        new GregorianCalendar(2017,0,29,11,00,00) | new GregorianCalendar(2017,1,10,12,00,00)

    }
}
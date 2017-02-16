package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.ReservationRequest
import com.example.booking.model.exception.BookingException
import com.example.booking.model.exception.BookingRuleViolationException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


/**
 * Created by deadlock on 15/2/17.
 */
class MaxTwoHourOnlySpec extends Specification {
    @Subject
    MaxTwoHourOnly maxTwoHourOnly = new MaxTwoHourOnly()

    ReservationRequest reservationRequest
    Bookable bookable = Mock(Bookable)


    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() duration is less or equal to two hours'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        maxTwoHourOnly.validate(reservationRequest, bookable)


        then:
        notThrown(BookingException)

        where:
        begin | end
        new GregorianCalendar(2017,0,30,11,00,00) | new GregorianCalendar(2017,0,30,13,00,00)
        new GregorianCalendar(2017,0,30,23,00,00) | new GregorianCalendar(2017,0,31,1,00,00)
        new GregorianCalendar(2017,0,30,22,00,00) | new GregorianCalendar(2017,0,31,00,00,00)
    }

    @Unroll
    'begin: #begin.getTime(), end: #end.getTime() duration is greater than two hours'(){
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()


        when:
        maxTwoHourOnly.validate(reservationRequest, bookable)


        then:
        thrown(BookingRuleViolationException)

        where:
        begin | end
        new GregorianCalendar(2017,0,30,11,00,00) | new GregorianCalendar(2017,0,30,14,00,00)
        new GregorianCalendar(2017,0,30,11,00,00) | new GregorianCalendar(2017,0,30,13,00,01)
        new GregorianCalendar(2017,0,30,23,00,00) | new GregorianCalendar(2017,0,31,1,00,01)
        new GregorianCalendar(2017,0,30,22,00,00) | new GregorianCalendar(2017,0,31,00,00,01)
        new GregorianCalendar(2017,0,30,11,00,00) | new GregorianCalendar(2017,0,31,11,00,00)
    }
}
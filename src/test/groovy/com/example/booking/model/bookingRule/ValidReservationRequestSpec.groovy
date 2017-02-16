package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.ReservationRequest
import com.example.booking.model.exception.BookingException
import com.example.booking.model.exception.InvalidReservationRequestException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


/**
 * Created by deadlock on 15/2/17.
 */
class ValidReservationRequestSpec extends Specification {
    Clock fixedClock = Clock.fixed(LocalDateTime.of(2017, 1, 26, 23, 00, 00).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

    @Subject
    ValidReservationRequest validReservationRequest = new ValidReservationRequest(fixedClock)

    ReservationRequest reservationRequest
    Bookable bookable = Mock(Bookable)

    Calendar time1100 = new GregorianCalendar(2017,0,31,11,00,00)
    Calendar time1300 = new GregorianCalendar(2017,0,31,13,00,00)

    def 'should throw InvalidReservationRequestException when begin field is null for create type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(null).end(time1100).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when begin field is missing in builder chain for create type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).end(time1100).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when end field is null for create type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).end(null).begin(time1100).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when end field is missing in builder chain for create type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(time1100).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when id is null for delete type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.DELETE).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when id is null for read type'(){
        when:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.READ).build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when id is null for update type'(){
        when:
        reservationRequest = ReservationRequest.builder()
                .requestType(ReservationRequest.RequestType.UPDATE)
                .reservationId(null)
                .begin(time1100)
                .end(time1300)
                .build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when begin is null for update type'(){
        when:
        reservationRequest = ReservationRequest.builder()
                .requestType(ReservationRequest.RequestType.UPDATE)
                .reservationId(1)
                .begin(null)
                .end(time1300)
                .build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    def 'should throw InvalidReservationRequestException when end is null for update type'(){
        when:
        reservationRequest = ReservationRequest.builder()
                .requestType(ReservationRequest.RequestType.UPDATE)
                .reservationId(1)
                .begin(time1100)
                .end(null)
                .build()
        validReservationRequest.validate(reservationRequest,bookable)

        then:
        thrown(InvalidReservationRequestException)
    }

    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() is valid reservation request'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        notThrown(BookingException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 30, 11, 00, 00) | new GregorianCalendar(2017, 0, 30, 13, 00, 00)
        new GregorianCalendar(2017, 0, 30, 23, 00, 00) | new GregorianCalendar(2017, 0, 31, 1, 00, 00)
        new GregorianCalendar(2017, 0, 31, 22, 00, 00) | new GregorianCalendar(2017, 1, 1, 00, 00, 00)
    }

    def 'reservation request is not valid if begin is not in future'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 13, 00, 00, 00) | new GregorianCalendar(2017, 0, 13, 2, 00, 00)
        new GregorianCalendar(2017, 0, 26, 22, 00, 00) | new GregorianCalendar(2017, 0, 26, 22, 30, 00)
        new GregorianCalendar(2017, 0, 26, 22, 00, 00) | new GregorianCalendar(2017, 0, 27, 00, 00, 00)
    }

    def 'reservation request is not valid if begin is after end'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 30, 13, 00, 00) | new GregorianCalendar(2017, 0, 30, 11, 00, 00)
        new GregorianCalendar(2017, 0, 31, 1, 00, 00)  | new GregorianCalendar(2017, 0, 28, 23, 00, 00)
        new GregorianCalendar(2017, 1, 1, 00, 00, 00)  | new GregorianCalendar(2017, 0, 31, 22, 00, 00)
    }

    def 'reservation request is not valid if begin is equal to end'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 30, 13, 00, 00) | new GregorianCalendar(2017, 0, 30, 13, 00, 00)
        new GregorianCalendar(2017, 0, 20, 13, 00, 00) | new GregorianCalendar(2017, 0, 20, 13, 00, 00)
    }

    def 'reservation request is not valid if begin - today > 7 days'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin                                         | end
        new GregorianCalendar(2017, 1, 2, 23, 00, 01) | new GregorianCalendar(2017, 1, 2, 23, 30, 00)
        new GregorianCalendar(2017, 1, 3, 13, 00, 00) | new GregorianCalendar(2017, 1, 3, 15, 00, 00)
    }

    def 'reservation request is not valid if not based on half hour time slot'() {
        given:
        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()

        when:
        validReservationRequest.validate(reservationRequest, bookable)


        then:
        thrown(InvalidReservationRequestException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 30, 11, 00, 10) | new GregorianCalendar(2017, 0, 30, 13, 00, 00)
        new GregorianCalendar(2017, 0, 30, 23, 00, 00) | new GregorianCalendar(2017, 0, 31, 1, 00, 10)
        new GregorianCalendar(2017, 0, 31, 22, 10, 00) | new GregorianCalendar(2017, 1, 1, 00, 00, 01)
    }
}
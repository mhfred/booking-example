package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.Reservation
import com.example.booking.model.ReservationRequest
import com.example.booking.model.exception.BookingRuleViolationException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


/**
 * Created by deadlock on 16/2/17.
 */
class NoOverlapForUpdateRequestSpec extends Specification {
    Bookable bookable
    ReservationRequest reservationRequest

    def reservation1130To1300 = Reservation.builder()
            .reservationId(1)
            .begin(new GregorianCalendar(2017, 0, 31, 11, 30, 00))
            .end(new GregorianCalendar(2017, 0, 31, 13, 00, 00))
            .build()
    def reservation1300To1500 = Reservation.builder()
            .reservationId(2)
            .begin(new GregorianCalendar(2017, 0, 31, 13, 00, 00))
            .end(new GregorianCalendar(2017, 0, 31, 15, 00, 00))
            .build()

    @Subject
    NoOverlapForUpdateRequest noOverlapForUpdateRequest = new NoOverlapForUpdateRequest()

    @Unroll
    'update to begin : #begin.getTime() , end : #end.getTime() is not overlap with existing reservations'() {
        given:
        reservationRequest = ReservationRequest.builder()
                .requestType(ReservationRequest.RequestType.UPDATE)
                .reservationId(1)
                .begin(begin)
                .end(end)
                .build()
        bookable = Mock(Bookable)
        bookable.getAllReservations() >> [reservation1130To1300, reservation1300To1500]

        when:
        noOverlapForUpdateRequest.validate(reservationRequest, bookable)


        then:
        notThrown(BookingRuleViolationException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 30, 11, 00, 00) | new GregorianCalendar(2017, 0, 30, 13, 00, 00)
        new GregorianCalendar(2017, 0, 31, 00, 00, 00) | new GregorianCalendar(2017, 0, 31, 1, 00, 00)
        new GregorianCalendar(2017, 0, 31, 15, 00, 00) | new GregorianCalendar(2017, 0, 31, 17, 00, 00)
        new GregorianCalendar(2017, 0, 31, 11, 00, 00) | new GregorianCalendar(2017, 0, 31, 13, 00, 00)
    }

    @Unroll
    'update to begin : #begin.getTime() , end : #end.getTime() is overlap with existing reservations'() {
        given:
        reservationRequest = ReservationRequest.builder()
                .requestType(ReservationRequest.RequestType.UPDATE)
                .reservationId(1)
                .begin(begin)
                .end(end)
                .build()
        bookable = Mock(Bookable)
        bookable.getAllReservations() >> [reservation1130To1300, reservation1300To1500]

        when:
        noOverlapForUpdateRequest.validate(reservationRequest, bookable)


        then:
        thrown(BookingRuleViolationException)

        where:
        begin                                          | end
        new GregorianCalendar(2017, 0, 31, 13, 30, 00) | new GregorianCalendar(2017, 0, 31, 14, 00, 00)
        new GregorianCalendar(2017, 0, 31, 11, 00, 00) | new GregorianCalendar(2017, 0, 31, 17, 00, 00)
        new GregorianCalendar(2017, 0, 31, 13, 30, 00) | new GregorianCalendar(2017, 0, 31, 14, 00, 00)
        new GregorianCalendar(2017, 0, 31, 13, 00, 00) | new GregorianCalendar(2017, 0, 31, 15, 00, 00)
    }
}
package com.example.booking.model.bookingRule

import com.example.booking.model.Bookable
import com.example.booking.model.Reservation
import com.example.booking.model.ReservationRequest
import com.example.booking.model.exception.BookingRuleViolationException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


/**
 * Created by deadlock on 15/2/17.
 */
class NoOverlapForCreateRequestSpec extends Specification {
    Bookable bookable
    ReservationRequest reservationRequest

    def reservation1130To1300 = Reservation.builder()
            .reservationId(1)
            .begin(new GregorianCalendar(2017, 0, 31, 11, 30, 00))
            .end(new GregorianCalendar(2017, 0, 31, 13, 00, 00))
            .build()
    def reservation2300To0100 = Reservation.builder()
            .reservationId(2)
            .begin(new GregorianCalendar(2017, 0, 31, 23, 00, 00))
            .end(new GregorianCalendar(2017, 1, 1, 01, 00, 00))
            .build()
    @Subject
    NoOverlapForCreateRequest noOverlapWithExistingReservation = new NoOverlapForCreateRequest()

    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() is not overlap with existing reservations'() {
        given:


        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)
        bookable.getAllReservations() >> [reservation1130To1300, reservation2300To0100]

        when:
        noOverlapWithExistingReservation.validate(reservationRequest, bookable)


        then:
        notThrown(BookingRuleViolationException)

        where:
        begin                                          | end

    }

    @Unroll
    'begin : #begin.getTime() , end : #end.getTime() is overlap with existing reservations'() {
        given:


        reservationRequest = ReservationRequest.builder().requestType(ReservationRequest.RequestType.CREATE).begin(begin).end(end).build()
        bookable = Mock(Bookable)
        bookable.getAllReservations() >> [reservation1130To1300, reservation2300To0100]

        when:
        noOverlapWithExistingReservation.validate(reservationRequest, bookable)


        then:
        thrown(BookingRuleViolationException)

        where:
        begin                                          | end

    }
}
package com.example.booking.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 11/2/17.
 */
class BookingItemFoosballSpec extends Specification {
    @Subject
    BookingItemFoosball bookingItemFoosball = new BookingItemFoosball()

    def 'can play'(){
        expect:
        bookingItemFoosball.play()
    }

    def 'can get non 0 and non empty id'(){
        expect:
        bookingItemFoosball.getBookingItemId()
    }

    def 'can get non null and non empty name'(){
        expect:
        bookingItemFoosball.getBookingItemName()
    }
}
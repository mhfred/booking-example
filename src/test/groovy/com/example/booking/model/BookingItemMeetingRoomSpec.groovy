package com.example.booking.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 11/2/17.
 */
class BookingItemMeetingRoomSpec extends Specification {
    @Subject
    BookingItemMeetingRoom bookingItemMeetingRoom = new BookingItemMeetingRoom()

    def 'can have meeting'(){
        expect:
        bookingItemMeetingRoom.meeting()
    }

    def 'can get non 0 and non empty id'(){
        expect:
        bookingItemMeetingRoom.getBookingItemId()
    }

    def 'can get non null and non empty name'(){
        expect:
        bookingItemMeetingRoom.getBookingItemName()
    }
}
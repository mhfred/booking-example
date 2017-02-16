package com.example.booking.model;

import com.example.meetingRoom.model.MeetingRoom;

/**
 * Created by deadlock on 7/2/17.
 */
public class BookingItemMeetingRoom extends MeetingRoom implements BookingItem {

    @Override
    public Integer getBookingItemId() {
        return 1;
    }

    @Override
    public String getBookingItemName() {
        return "meeting room";
    }
}

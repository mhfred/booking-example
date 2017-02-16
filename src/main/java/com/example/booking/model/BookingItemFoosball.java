package com.example.booking.model;

import com.example.foosball.model.Foosball;

/**
 * Created by deadlock on 7/2/17.
 */
public class BookingItemFoosball extends Foosball implements BookingItem {

    @Override
    public Integer getBookingItemId() {
        return 2;
    }

    @Override
    public String getBookingItemName() {
        return "foosball";
    }
}

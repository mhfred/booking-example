package com.example.booking.model;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

/**
 * Created by deadlock on 7/2/17.
 */
@Data
@Builder
public class Reservation {
    private Long reservationId;
    private Calendar begin;
    private Calendar end;
}

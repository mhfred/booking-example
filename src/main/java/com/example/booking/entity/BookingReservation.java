package com.example.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by deadlock on 15/2/17.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingReservation {
    @Id
    @GeneratedValue
    private Long id;

    private Integer itemId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar begin;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar end;
}

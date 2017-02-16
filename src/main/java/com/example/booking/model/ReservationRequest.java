package com.example.booking.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Calendar;

/**
 * Created by deadlock on 16/2/17.
 */
@Builder
@Data
public class ReservationRequest {

    public enum RequestType {
        CREATE(1),
        READ(2),
        UPDATE(3),
        DELETE(4);

        public final int type;

        RequestType(int type) {
            this.type = type;
        }
    }

    @NonNull
    private RequestType requestType;

    private Long reservationId;
    private Calendar begin;
    private Calendar end;
}

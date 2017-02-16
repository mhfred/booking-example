package com.example.booking.model.exception;

/**
 * Created by deadlock on 7/2/17.
 */
public class InvalidReservationRequestException extends BookingException {
    public InvalidReservationRequestException() {
        super();
    }

    public InvalidReservationRequestException(String message) {
        super(message);
    }

    public InvalidReservationRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationRequestException(Throwable cause) {
        super(cause);
    }

    protected InvalidReservationRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

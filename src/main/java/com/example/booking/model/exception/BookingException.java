package com.example.booking.model.exception;

/**
 * Created by deadlock on 7/2/17.
 */
public class BookingException extends Exception {
    public BookingException() {
        super();
    }

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(Throwable cause) {
        super(cause);
    }

    protected BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

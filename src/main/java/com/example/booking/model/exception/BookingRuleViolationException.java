package com.example.booking.model.exception;

/**
 * Created by deadlock on 11/2/17.
 */
public class BookingRuleViolationException extends BookingException {
    public BookingRuleViolationException() {
        super();
    }

    public BookingRuleViolationException(String message) {
        super(message);
    }

    public BookingRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingRuleViolationException(Throwable cause) {
        super(cause);
    }

    protected BookingRuleViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

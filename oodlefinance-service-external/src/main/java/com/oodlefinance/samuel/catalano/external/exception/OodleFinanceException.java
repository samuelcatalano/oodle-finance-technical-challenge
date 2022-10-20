package com.oodlefinance.samuel.catalano.external.exception;

public class OodleFinanceException extends Exception {

    public OodleFinanceException() {
        super();
    }

    public OodleFinanceException(String message) {
        super(message);
    }

    public OodleFinanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OodleFinanceException(Throwable cause) {
        super(cause);
    }

    protected OodleFinanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

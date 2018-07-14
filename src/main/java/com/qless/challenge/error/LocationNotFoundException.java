package com.qless.challenge.error;

public class LocationNotFoundException extends RuntimeException{

    public LocationNotFoundException() {
        super();
    }

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFoundException(Throwable cause) {
        super(cause);
    }
}

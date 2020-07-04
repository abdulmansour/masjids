package com.qualgo.masjids.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class TimingNotFoundException extends ResourceNotFoundException {
    public TimingNotFoundException() {
        super();
    }

    public TimingNotFoundException(String message) {
        super(message);
    }

    public TimingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

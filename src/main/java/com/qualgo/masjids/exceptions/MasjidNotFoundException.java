package com.qualgo.masjids.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class MasjidNotFoundException extends ResourceNotFoundException {
    public MasjidNotFoundException() {
        super();
    }

    public MasjidNotFoundException(String message) {
        super(message);
    }

    public MasjidNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

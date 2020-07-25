package com.qualgo.masjids.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;


public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
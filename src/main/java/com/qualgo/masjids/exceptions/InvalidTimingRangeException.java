package com.qualgo.masjids.exceptions;

import java.security.InvalidParameterException;

public class InvalidTimingRangeException extends InvalidParameterException {
    public InvalidTimingRangeException() {
        super();
    }

    public InvalidTimingRangeException(String msg) {
        super(msg);
    }
}

package br.com.myApp.MyApp.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

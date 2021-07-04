package br.com.myApp.MyApp.exceptions;

public class BadRequestException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

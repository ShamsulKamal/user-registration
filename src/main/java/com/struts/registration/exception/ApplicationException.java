package com.struts.registration.exception;


public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -2853171857924541653L;

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(Throwable cause) {
        super(cause);
    }
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

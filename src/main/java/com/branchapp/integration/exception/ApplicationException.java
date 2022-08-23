package com.branchapp.integration.exception;

public class ApplicationException extends RuntimeException {

    public enum ExceptionCode {
        UNKNOWN, RESOURCE_NOT_FOUND
    }

    private final ExceptionCode code;

    public ApplicationException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }
}

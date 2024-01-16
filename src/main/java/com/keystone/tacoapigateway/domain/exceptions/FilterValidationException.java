package com.keystone.tacoapigateway.domain.exceptions;

public class FilterValidationException extends RuntimeException {

    public final Integer status;

    protected FilterValidationException(final String message, final Integer status) {
        super(message);
        this.status = status;
    }
}

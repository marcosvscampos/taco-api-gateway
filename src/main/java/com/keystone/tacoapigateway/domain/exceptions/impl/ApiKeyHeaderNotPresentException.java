package com.keystone.tacoapigateway.domain.exceptions.impl;

import com.keystone.tacoapigateway.domain.exceptions.FilterValidationException;

public class ApiKeyHeaderNotPresentException extends FilterValidationException {

    public ApiKeyHeaderNotPresentException() {
        super("Header 'x-api-key' is required", 401);
    }
}

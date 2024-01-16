package com.keystone.tacoapigateway.domain.exceptions.impl;

import com.keystone.tacoapigateway.domain.exceptions.FilterValidationException;

public class InvalidApiKeyValueException extends FilterValidationException {
    public InvalidApiKeyValueException() {
        super("Value in Header 'x-api-key' is invalid", 401);
    }
}

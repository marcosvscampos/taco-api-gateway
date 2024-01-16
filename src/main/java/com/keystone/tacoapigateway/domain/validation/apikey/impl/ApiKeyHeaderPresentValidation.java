package com.keystone.tacoapigateway.domain.validation.apikey.impl;

import com.keystone.tacoapigateway.domain.exceptions.FilterValidationException;
import com.keystone.tacoapigateway.domain.exceptions.impl.ApiKeyHeaderNotPresentException;
import com.keystone.tacoapigateway.domain.validation.apikey.ApiKeyValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ApiKeyHeaderPresentValidation extends ApiKeyValidation {

    @Override
    protected Mono<Boolean> isValid(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        return Mono.just(headers.containsKey(X_API_KEY));
    }

    @Override
    protected FilterValidationException throwError() {
        log.error("[ApiKeyHeaderNotPresentValidation#throwError] No 'x-api-key' value has been informed");
        return new ApiKeyHeaderNotPresentException();
    }
}

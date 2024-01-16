package com.keystone.tacoapigateway.domain.validation.apikey.impl;

import com.keystone.tacoapigateway.domain.exceptions.FilterValidationException;
import com.keystone.tacoapigateway.domain.exceptions.impl.InvalidApiKeyValueException;
import com.keystone.tacoapigateway.domain.usecases.FindApiKeyUseCase;
import com.keystone.tacoapigateway.domain.validation.apikey.ApiKeyValidation;
import com.keystone.tacoapigateway.infrastructure.repository.ApiKeyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
public class ApiKeyAuthenticValueValidation extends ApiKeyValidation {

    public static final String X_API_KEY = "x-api-key";

    private final FindApiKeyUseCase findApiKeyUseCase;

    public ApiKeyAuthenticValueValidation(final FindApiKeyUseCase findApiKeyUseCase) {
        this.findApiKeyUseCase = findApiKeyUseCase;
    }

    @Override
    protected Mono<Boolean> isValid(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String apiKeyValue = headers.getFirst(X_API_KEY);

        return findApiKeyUseCase.findByKey(apiKeyValue)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalKey -> {
                    if (optionalKey.isEmpty()) {
                        return Mono.just(Boolean.FALSE);
                    }
                    return Mono.just(Boolean.TRUE);
                });
    }

    @Override
    protected FilterValidationException throwError() {
        log.error("[ApiKeyAuthenticValueValidation#throwError] Value for 'x-api-key' is invalid or was not found!");
        return new InvalidApiKeyValueException();
    }
}

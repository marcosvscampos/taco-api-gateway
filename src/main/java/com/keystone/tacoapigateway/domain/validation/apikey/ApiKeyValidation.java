package com.keystone.tacoapigateway.domain.validation.apikey;

import com.keystone.tacoapigateway.domain.exceptions.FilterValidationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

public abstract class ApiKeyValidation {

    private ApiKeyValidation next;

    public static final String X_API_KEY = "x-api-key";

    public static ApiKeyValidation chain(ApiKeyValidation first, ApiKeyValidation... chain){
        ApiKeyValidation head = first;
        for (ApiKeyValidation nextInChain: chain){
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public Mono<Void> validate(final ServerWebExchange exchange, final GatewayFilterChain chain) {

        return isValid(exchange)
                .flatMap(isValid -> {
                    if(Boolean.TRUE.equals(isValid)) {
                        if (Objects.isNull(this.next)) {
                            return chain.filter(exchange);
                        }
                        return next.validate(exchange, chain);
                    }
                    return Mono.error(this.throwError());
                });
    }

    protected abstract Mono<Boolean> isValid(final ServerWebExchange exchange);

    protected abstract FilterValidationException throwError();
}

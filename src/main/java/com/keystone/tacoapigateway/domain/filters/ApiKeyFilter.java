package com.keystone.tacoapigateway.domain.filters;

import com.keystone.tacoapigateway.domain.usecases.FindApiKeyUseCase;
import com.keystone.tacoapigateway.domain.validation.apikey.ApiKeyValidation;
import com.keystone.tacoapigateway.domain.validation.apikey.impl.ApiKeyAuthenticValueValidation;
import com.keystone.tacoapigateway.domain.validation.apikey.impl.ApiKeyHeaderPresentValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ApiKeyFilter implements GatewayFilter {

    private final ApiKeyValidation validation;

    public ApiKeyFilter(final FindApiKeyUseCase findApiKeyUseCase) {
        this.validation = ApiKeyValidation.chain(
                new ApiKeyHeaderPresentValidation(),
                new ApiKeyAuthenticValueValidation(findApiKeyUseCase)
        );
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[ApiKeyFilter#filter] Starting 'api-key' validation");
        return validation.validate(exchange, chain)
                .doOnSuccess(s -> log.info("[ApiKeyFilter#filter] Validations for 'api-key' completed successfully"));
    }
}

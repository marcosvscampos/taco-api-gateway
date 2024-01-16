package com.keystone.tacoapigateway.domain.usecases;

import com.keystone.tacoapigateway.infrastructure.model.ApiKey;
import reactor.core.publisher.Mono;

public interface FindApiKeyUseCase {

    Mono<ApiKey> findByKey(final String value);

}

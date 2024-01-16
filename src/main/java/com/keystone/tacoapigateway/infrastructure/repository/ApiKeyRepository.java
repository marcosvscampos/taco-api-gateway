package com.keystone.tacoapigateway.infrastructure.repository;

import com.keystone.tacoapigateway.infrastructure.model.ApiKey;
import reactor.core.publisher.Mono;

public interface ApiKeyRepository {

    Mono<ApiKey> findByKey(final String key);

}

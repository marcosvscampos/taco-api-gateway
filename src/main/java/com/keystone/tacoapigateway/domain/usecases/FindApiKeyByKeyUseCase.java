package com.keystone.tacoapigateway.domain.usecases;

import com.keystone.tacoapigateway.infrastructure.model.ApiKey;
import com.keystone.tacoapigateway.infrastructure.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindApiKeyByKeyUseCase implements FindApiKeyUseCase {

    @Qualifier("reactiveMongoApiKeyRepository")
    private ApiKeyRepository apiKeyRepository;

    public Mono<ApiKey> findByKey(final String key){
        return apiKeyRepository.findByKey(key);
    }
}

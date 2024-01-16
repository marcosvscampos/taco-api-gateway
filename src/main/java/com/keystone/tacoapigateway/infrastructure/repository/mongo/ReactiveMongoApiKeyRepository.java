package com.keystone.tacoapigateway.infrastructure.repository.mongo;

import com.keystone.tacoapigateway.infrastructure.model.ApiKey;
import com.keystone.tacoapigateway.infrastructure.repository.ApiKeyRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongoApiKeyRepository extends ApiKeyRepository, ReactiveMongoRepository<ApiKey, String> {
}

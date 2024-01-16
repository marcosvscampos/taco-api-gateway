package com.keystone.tacoapigateway.infrastructure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "ApiKeys")
public class ApiKey {

    @Id
    private String id;

    private String key;

    @Field("user_id")
    private String userId;
}

package com.keystone.tacoapigateway.application.config.routes.impl;

import com.keystone.tacoapigateway.application.config.routes.RouteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetUserByIdRouteConfig implements RouteConfig {

    @Value("${service.uri.taco-api-users-service}")
    private String serviceUri;

    @Override
    public void configure(RouteLocatorBuilder.Builder routeBuilder) {
        log.info(">>> Route Available: {} - {}{}", "GET", serviceUri, "/api/users/{id}");
        routeBuilder.route("get_user_by_id", r -> r.method("GET")
                .and()
                .path("/api/users/{id}")
                .uri(serviceUri));

    }
}

package com.keystone.tacoapigateway.application.config.routes.impl;

import com.keystone.tacoapigateway.application.config.routes.RouteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostUsersRouteConfig implements RouteConfig {

    @Value("${service.uri.taco-api-users-service}")
    private String serviceUri;

    @Override
    public void configure(final RouteLocatorBuilder.Builder routeBuilder) {
        log.info(">>> Route Available: {} - {}{}","POST", serviceUri, "/api/users");
        routeBuilder.route("post_users", r -> r.method("POST")
                .and()
                .path("/api/users")
                .uri(serviceUri));
    }
}

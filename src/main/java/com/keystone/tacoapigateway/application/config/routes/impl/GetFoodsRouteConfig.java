package com.keystone.tacoapigateway.application.config.routes.impl;

import com.keystone.tacoapigateway.application.config.routes.RouteConfig;
import com.keystone.tacoapigateway.domain.filters.ApiKeyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetFoodsRouteConfig implements RouteConfig {

    @Value("${service.uri.taco-api-foods-service}")
    private String serviceUri;

    private final ApiKeyFilter apiKeyFilter;

    public GetFoodsRouteConfig(final ApiKeyFilter apiKeyFilter) {
        this.apiKeyFilter = apiKeyFilter;
    }

    @Override
    public void configure(RouteLocatorBuilder.Builder routeBuilder) {
        log.info(">>> Route Available: {} - {}{}", "GET", serviceUri, "/api/foods");
        routeBuilder.route("get_foods", r -> r.method("GET")
                .and()
                .path("/api/foods")
                .filters(f -> f.filters(this.apiKeyFilter))
                .uri(serviceUri));
    }
}

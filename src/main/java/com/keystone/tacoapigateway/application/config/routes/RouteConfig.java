package com.keystone.tacoapigateway.application.config.routes;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

public interface RouteConfig {

    void configure(final RouteLocatorBuilder.Builder routeBuilder);

}

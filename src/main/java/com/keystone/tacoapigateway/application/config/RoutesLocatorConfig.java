package com.keystone.tacoapigateway.application.config;

import com.keystone.tacoapigateway.application.config.routes.RouteConfig;
import com.keystone.tacoapigateway.domain.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoutesLocatorConfig {

    private final List<RouteConfig> routeConfigList;

    public RoutesLocatorConfig(List<RouteConfig> configs) {
        this.routeConfigList = configs;
    }

    @Bean
    public RouteLocator routeLocator(final RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routeConfigList.forEach(rc -> rc.configure(routes));
        return routes.build();
    }
}

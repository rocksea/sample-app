/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import kr.co.sample.gateway.filter.RouterFilter;
import kr.co.sample.gateway.filter.RouterFilterBlocking;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired private RouterFilter routerFilter;
    @Autowired private RouterFilterBlocking routerFilterBlocking;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        "rewrite_route",
                        r -> r.path("/hello").filters(f -> f.filter(routerFilter)).uri("http://httpbin.org"))
                .route(
                        "rewrite_route_blocking",
                        r ->
                                r.path("/world")
                                        .filters(f -> f.filter(routerFilterBlocking))
                                        .uri("http://httpbin.org"))
                .route("rewrite_route_nonfilter", r -> r.path("/hello2").uri("http://httpbin.org"))
                .build();
    }
}

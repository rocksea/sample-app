/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.gateway.filter;

import java.util.concurrent.ExecutionException;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import kr.co.sample.gateway.infrastructure.model.User;
import kr.co.sample.gateway.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class RouterFilterBlocking implements GatewayFilter {
    private final UserRepository userRepository;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("request.getPath : {}", request.getPath());
        log.info("request.getURI() : {}", request.getURI());
        log.info("request.getCookies() : {}", request.getCookies());
        log.info("request.getHeaders() : {}", request.getHeaders());
        log.info("##라우팅 response cookies: {}", exchange.getResponse().getCookies());
        Mono<Long> result = userRepository.save(User.builder().id("1").age(10).name("rocksea").build());
        try {
            result.toFuture().get();
            Mono<User> user = userRepository.findById("1");
            user.toFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }
}

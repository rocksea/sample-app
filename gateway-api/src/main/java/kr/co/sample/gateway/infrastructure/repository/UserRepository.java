/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.gateway.infrastructure.repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import kr.co.sample.gateway.infrastructure.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final ReactiveRedisOperations<String, User> reactiveRedisOperations;

    public Flux<User> findAll() {
        return this.reactiveRedisOperations.opsForList().range("users", 0, -1);
    }

    public Mono<User> findById(String id) {
        return this.findAll().filter(p -> p.getId().equals(id)).last();
    }

    public Mono<Long> save(User user) {
        return this.reactiveRedisOperations.opsForList().rightPush("users", user);
    }

    public Mono<Boolean> deleteAll() {
        return this.reactiveRedisOperations.opsForList().delete("users");
    }
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.gateway.infrastructure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("users")
public class User {
    @Id private String id;
    private String name;
    private Integer age;
}

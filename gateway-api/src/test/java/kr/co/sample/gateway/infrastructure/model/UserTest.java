/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.gateway.infrastructure.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void createUserTest() {
        User user = User.builder().id("1").name("rocksea").age(10).build();
        assertThat(user).isNotNull();
        assertThat(user.getName()).isNull();
    }
}

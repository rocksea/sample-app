/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "kr.co.sample.coupon.infrastructure")
@Configuration
public class FeignConfig {}

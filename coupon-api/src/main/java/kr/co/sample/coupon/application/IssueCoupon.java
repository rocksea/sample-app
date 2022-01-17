/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.application;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class IssueCoupon {
    @KafkaListener(topics = "issue_coupon", groupId = "coupon")
    public void consume(String message) throws IOException {
        System.out.printf("Consumed message : %s%n", message);
    }
}

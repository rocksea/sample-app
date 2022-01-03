/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
    private Integer memberId;
    private String name;
    private Integer age;
}

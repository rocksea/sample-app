/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.vo;

import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class CouponId {
    private UUID id;
}

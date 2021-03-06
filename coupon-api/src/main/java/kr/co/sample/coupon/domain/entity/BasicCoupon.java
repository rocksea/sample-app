/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.entity;

import javax.persistence.*;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Entity
@DiscriminatorValue("P")
public class BasicCoupon extends Coupon {
    @Column private String grade;

    public BasicCoupon() {}
}

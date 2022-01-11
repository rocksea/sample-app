/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.aggregate;

import javax.persistence.*;

import kr.co.sample.coupon.domain.entity.BaseEntity;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(of = "id", callSuper = false)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Coupon extends BaseEntity {
    // @EmbeddedId
    // private CouponId id;

    @Id @GeneratedValue private Integer id;

    private CouponType couponType;
    private String name;
    private DiscountType discountType;
    private Short rate;
    private Integer amount;

    protected Coupon() {}
}

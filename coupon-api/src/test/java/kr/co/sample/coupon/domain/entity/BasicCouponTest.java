/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;

public class BasicCouponTest {
    @Test
    @DisplayName("같은 ID를 가진 베이직쿠폰은 equals 시 같아야한다")
    public void basicCoupon_should_be_euqal() {
        Coupon basicCoupon =
                TargetCoupon.builder()
                        .id(1)
                        .name("basicCoupon")
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();

        Coupon basicCoupon2 =
                TargetCoupon.builder()
                        .id(1)
                        .name("basicCoupon111111")
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(2000)
                        .build();
        assertThat(basicCoupon.equals(basicCoupon2)).isTrue();
    }
}

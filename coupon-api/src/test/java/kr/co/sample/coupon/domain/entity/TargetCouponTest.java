/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;

public class TargetCouponTest {
    @Test
    @DisplayName("같은 ID를 타겟쿠폰은 equals 시 같아야한다")
    public void targetCoupon_should_be_equal() {
        Coupon targetCoupon =
                TargetCoupon.builder()
                        .id(1)
                        .targetMemberIds(Set.of(1, 2, 3))
                        .name("targetCoupon")
                        .couponType(CouponType.TARGET)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();

        Coupon targetCoupon2 =
                TargetCoupon.builder()
                        .id(1)
                        .name("targetCoupon1111111")
                        .targetMemberIds(Set.of(1, 2, 3))
                        .couponType(CouponType.TARGET)
                        .discountType(DiscountType.AMOUNT)
                        .amount(2000)
                        .build();
        assertThat(targetCoupon.equals(targetCoupon2)).isTrue();
    }

    @Test
    @DisplayName("Issuable 쿠폰 테스트")
    public void targetCoupon_should_be_issuable() {
        Set memberIds = Set.of(1, 2, 3);
        Coupon targetCoupon =
                TargetCoupon.builder()
                        .id(1)
                        .name("targetCoupon")
                        .targetMemberIds(memberIds)
                        .couponType(CouponType.TARGET)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();

        assertThat(targetCoupon instanceof TargetCoupon).isTrue();
        assertThat(((TargetCoupon) targetCoupon).getTargetMemberIds()).isEqualTo(memberIds);
        assertThat(((TargetCoupon) targetCoupon).isIssuable(1)).isTrue();
        assertThat(((TargetCoupon) targetCoupon).isIssuable(4)).isFalse();
    }
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query;

import java.util.List;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CouponQueryResult {
    private Integer id;
    private String name;
    private CouponType couponType;
    private DiscountType discountType;
    private Integer amount;
    private Short rate;
    private List<Integer> memberIds;

    public static CouponQueryResult fromCoupon(Coupon coupon) {
        return CouponQueryResult.builder()
                .id(coupon.getId())
                .couponType(coupon.getCouponType())
                .discountType(coupon.getDiscountType())
                .amount(coupon.getAmount())
                .rate(coupon.getRate())
                .name(coupon.getName())
                .build();
    }
}

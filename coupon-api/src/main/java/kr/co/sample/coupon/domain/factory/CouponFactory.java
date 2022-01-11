/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.factory;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.command.AddNewCoupon;
import kr.co.sample.coupon.domain.entity.BasicCoupon;
import kr.co.sample.coupon.domain.entity.TargetCoupon;
import lombok.NonNull;

public class CouponFactory {
    public static Coupon createFrom(@NonNull AddNewCoupon addNewCoupon) {
        Coupon coupon = null;
        switch (addNewCoupon.getCouponType()) {
            case BASIC:
                coupon =
                        BasicCoupon.builder()
                                .couponType(addNewCoupon.getCouponType())
                                .name(addNewCoupon.getName())
                                .amount(addNewCoupon.getAmount())
                                .rate(addNewCoupon.getRate())
                                .discountType(addNewCoupon.getDiscountType())
                                .build();
                break;
            case TARGET:
                coupon =
                        TargetCoupon.builder()
                                .couponType(addNewCoupon.getCouponType())
                                .name(addNewCoupon.getName())
                                .amount(addNewCoupon.getAmount())
                                .rate(addNewCoupon.getRate())
                                .discountType(addNewCoupon.getDiscountType())
                                .targetMemberIds(addNewCoupon.getMemberIds())
                                .build();
                break;
        }

        return coupon;
    }
}

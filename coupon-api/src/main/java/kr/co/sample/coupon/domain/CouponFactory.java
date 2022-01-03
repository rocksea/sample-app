/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain;

import kr.co.sample.coupon.domain.entity.CouponMember;

public class CouponFactory {
    public static Coupon of(String name, Integer memberId, String memberName, Integer age) {
        Coupon coupon = Coupon.builder().name(name).build();
        CouponMember couponMember =
                CouponMember.builder().coupon(coupon).memberId(memberId).name(memberName).age(age).build();
        coupon.setCouponMember(couponMember);
        return coupon;
    }
}

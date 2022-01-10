/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation.http.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCouponParam {
    @NotNull private CouponType couponType;
    @NotNull private String name;
    @NotNull private DiscountType discountType;
    private Short rate;
    private Integer amount;
    private Set<Integer> memberIds;
    private Set<Integer> productIds;
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.command;

import java.util.Set;

import kr.co.sample.core.cqrs.command.Command;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AddNewCoupon implements Command {
    private Integer id;
    @NonNull private CouponType couponType;
    @NonNull private DiscountType discountType;
    @NonNull private String name;
    private Short rate;
    private Integer amount;
    private Set<Integer> memberIds;
    private Set<Integer> productIds;
}

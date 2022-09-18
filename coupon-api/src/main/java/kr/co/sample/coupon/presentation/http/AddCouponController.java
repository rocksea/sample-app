/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation.http;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.coupon.domain.command.AddNewCoupon;
import kr.co.sample.coupon.presentation.http.request.AddCouponParam;

@RestController
@RequestMapping(value = "/coupon/v1/coupons")
public class AddCouponController {
    private final CommandHandler<AddNewCoupon> addNewCouponCommandHandler;

    public AddCouponController(CommandHandler<AddNewCoupon> addNewCouponCommandHandler) {
        this.addNewCouponCommandHandler = addNewCouponCommandHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCoupon(@Valid @RequestBody AddCouponParam couponParam) {
        AddNewCoupon addNewCoupon =
                AddNewCoupon.builder()
                        .name(couponParam.getName())
                        .couponType(couponParam.getCouponType())
                        .discountType(couponParam.getDiscountType())
                        .rate(couponParam.getRate())
                        .amount(couponParam.getAmount())
                        .memberIds(couponParam.getMemberIds())
                        .productIds(couponParam.getProductIds())
                        .build();
        addNewCouponCommandHandler.handle(addNewCoupon);
    }
}

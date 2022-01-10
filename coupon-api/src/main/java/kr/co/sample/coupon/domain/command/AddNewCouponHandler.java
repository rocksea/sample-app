/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.command;

import org.springframework.stereotype.Service;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.aggregate.CouponFactory;
import kr.co.sample.coupon.domain.repository.CouponRepository;

@Service
public class AddNewCouponHandler implements CommandHandler<AddNewCoupon> {
    private final CouponRepository couponRepository;

    public AddNewCouponHandler(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public void handle(AddNewCoupon command) {
        Coupon coupon = CouponFactory.createFrom(command);
        couponRepository.save(coupon);
    }
}

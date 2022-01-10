/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query;

import org.springframework.stereotype.Service;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.query.exception.CouponIsNotIssuableException;
import kr.co.sample.coupon.domain.query.exception.CouponNotFoundException;
import kr.co.sample.coupon.infrastructure.CouponQueryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponQueryService {
    private final CouponQueryRepository couponQueryRepository;

    public CouponQueryResult getCouponById(Integer couponId) {
        Coupon coupon =
                couponQueryRepository
                        .findCouponById(couponId)
                        .orElseThrow(() -> new CouponNotFoundException("쿠폰이 존재하지 않습니다."));
        return CouponQueryResult.fromCoupon(coupon);
    }

    public CouponQueryResult getCouponIssuableByCouponIdAndMemberId(
            Integer couponId, Integer memberId) {
        Coupon coupon =
                couponQueryRepository
                        .findCouponIsIssuableById(couponId, memberId)
                        .orElseThrow(() -> new CouponIsNotIssuableException("발급가능한 쿠폰이 존재하지 않습니다."));
        return CouponQueryResult.fromCoupon(coupon);
    }
}

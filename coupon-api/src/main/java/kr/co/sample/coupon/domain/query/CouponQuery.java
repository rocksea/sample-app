/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query;

import org.springframework.stereotype.Service;

import kr.co.sample.coupon.domain.Coupon;
import kr.co.sample.coupon.domain.query.exception.CouponNotFoundException;
import kr.co.sample.coupon.domain.repository.CouponRepository;
import kr.co.sample.coupon.domain.vo.Member;

@Service
public class CouponQuery {
    private final CouponRepository couponRepository;

    public CouponQuery(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponQueryResult getCouponById(Integer id) {
        Coupon coupon =
                couponRepository
                        .findById(id)
                        .orElseThrow(() -> new CouponNotFoundException("쿠폰이 존재하지 않습니다."));
        Member member =
                Member.builder()
                        .memberId(coupon.getCouponMember().getMemberId())
                        .name(coupon.getCouponMember().getName())
                        .age(coupon.getCouponMember().getAge())
                        .build();
        CouponQueryResult memberQueryResult =
                CouponQueryResult.builder()
                        .id(coupon.getId())
                        .name(coupon.getName())
                        .member(member)
                        .build();
        return memberQueryResult;
    }
}

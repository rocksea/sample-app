/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.infrastructure;

import static kr.co.sample.coupon.domain.aggregate.QCoupon.coupon;
import static kr.co.sample.coupon.domain.aggregate.QTargetCoupon.targetCoupon;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.sample.coupon.domain.aggregate.Coupon;

@Repository
public class CouponQueryRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public CouponQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(CouponQueryRepository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<Coupon> findCouponById(Integer couponId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(coupon).where(coupon.id.eq(couponId)).fetchOne());
    }

    public Optional<Coupon> findCouponIsIssuableById(Integer couponId, Integer memberId) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(targetCoupon)
                        .innerJoin(targetCoupon.targetMemberIds)
                        .where(targetCoupon.id.eq(couponId), targetCoupon.targetMemberIds.contains(memberId))
                        .fetchOne());
    }
}

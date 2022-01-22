/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.co.sample.coupon.application.CouponQueryService;
import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.entity.BasicCoupon;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import kr.co.sample.coupon.infrastructure.CouponQueryRepository;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("CouponQueryService Tests")
public class CouponQueryTest {
    @Mock private CouponQueryRepository couponQueryRepository;

    @Test
    @DisplayName("베이직쿠폰이 검색되어야한다")
    public void basicCoupon_should_be_found() {
        Coupon basicCoupon =
                BasicCoupon.builder()
                        .id(1)
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .rate((short) 0)
                        .name("rocksea's coupon")
                        .build();

        when(couponQueryRepository.findCouponById(anyInt())).thenReturn(Optional.of(basicCoupon));
        CouponQueryService couponQueryService = new CouponQueryService(couponQueryRepository);

        CouponQueryResult couponQueryResult = couponQueryService.getCouponById(basicCoupon.getId());
        verify(couponQueryRepository, times(1)).findCouponById(anyInt());

        assertThat(couponQueryResult).isNotNull();
        assertThat(couponQueryResult.getId()).isEqualTo(basicCoupon.getId());
        assertThat(couponQueryResult.getCouponType()).isEqualTo(basicCoupon.getCouponType());
    }

    @Test
    @DisplayName("타겟쿠폰이 검색되어야한다")
    public void targetCoupon_should_be_found() {
        Coupon basicCoupon =
                BasicCoupon.builder()
                        .id(1)
                        .couponType(CouponType.TARGET)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .rate((short) 0)
                        .name("rocksea's coupon")
                        .build();

        when(couponQueryRepository.findCouponById(anyInt())).thenReturn(Optional.of(basicCoupon));
        CouponQueryService couponQueryService = new CouponQueryService(couponQueryRepository);

        CouponQueryResult couponQueryResult = couponQueryService.getCouponById(basicCoupon.getId());
        verify(couponQueryRepository, times(1)).findCouponById(anyInt());

        assertThat(couponQueryResult).isNotNull();
        assertThat(couponQueryResult.getId()).isEqualTo(basicCoupon.getId());
        assertThat(couponQueryResult.getCouponType()).isEqualTo(basicCoupon.getCouponType());
    }
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.command;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.coupon.application.AddNewCouponHandler;
import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.repository.CouponRepository;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AddNewCouponCommand Tests")
public class AddNewCouponCommandTest {
    @Mock private CouponRepository couponRepository;

    @Test
    @DisplayName("새로운 베이직쿠폰이 등록되어야한다")
    public void basicCoupon_should_be_created() {
        when(couponRepository.save(any(Coupon.class))).thenReturn(any(Coupon.class));
        CommandHandler<AddNewCoupon> addNewMemberCommandHandler =
                new AddNewCouponHandler(couponRepository);
        AddNewCoupon addNewCoupon =
                AddNewCoupon.builder()
                        .id(1)
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .rate((short) 0)
                        .name("rocksea's coupon")
                        .build();
        addNewMemberCommandHandler.handle(addNewCoupon);

        verify(couponRepository, times(1)).save(any(Coupon.class));
    }

    @Test
    @DisplayName("새로운 타겟쿠폰이 등록되어야한다")
    public void targetCoupon_should_be_created() {
        when(couponRepository.save(any(Coupon.class))).thenReturn(any(Coupon.class));
        CommandHandler<AddNewCoupon> addNewMemberCommandHandler =
                new AddNewCouponHandler(couponRepository);
        AddNewCoupon addNewCoupon =
                AddNewCoupon.builder()
                        .id(1)
                        .couponType(CouponType.TARGET)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .rate((short) 0)
                        .name("rocksea's coupon")
                        .build();
        addNewMemberCommandHandler.handle(addNewCoupon);

        verify(couponRepository, times(1)).save(any(Coupon.class));
    }
}

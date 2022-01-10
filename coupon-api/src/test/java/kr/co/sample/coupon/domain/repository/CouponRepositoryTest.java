/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.sample.coupon.domain.aggregate.BasicCoupon;
import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;

@DataJpaTest
@EnableJpaRepositories(basePackages = {"kr.co.sample.coupon.*"})
@EntityScan("kr.co.sample.coupon")
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("CouponRepository Tests")
public class CouponRepositoryTest {
    @Autowired private CouponRepository couponRepository;

    Coupon basicCoupon;

    @BeforeAll
    void setup() {
        basicCoupon =
                BasicCoupon.builder()
                        .name("BasicCoupon")
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();
    }

    @Test
    @Rollback(false)
    @DisplayName("BasicCoupon Insert test")
    public void basicCouponShouldBeSaved() {
        Coupon savedCoupon = couponRepository.save(basicCoupon);
        assertThat(basicCoupon).isNotNull().isEqualTo(savedCoupon);
        couponRepository.delete(basicCoupon);
    }

    @Test
    @DisplayName("BasicCoupon Delete test")
    public void basicCouponShouldBeDeleted() {
        assertThrows(
                EntityNotFoundException.class,
                () -> {
                    Coupon deletedCoupon = couponRepository.getById(basicCoupon.getId());
                    assertThat(deletedCoupon).isNull();
                });
    }
}

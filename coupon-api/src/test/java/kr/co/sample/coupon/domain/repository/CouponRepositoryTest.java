/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import kr.co.sample.coupon.domain.entity.BasicCoupon;
import kr.co.sample.coupon.domain.query.exception.CouponNotFoundException;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;

@DataJpaTest
@EnableJpaRepositories(basePackages = {"kr.co.sample.coupon.*"})
@EntityScan("kr.co.sample.coupon")
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("CouponRepository Tests")
public class CouponRepositoryTest {
    @Autowired private CouponRepository couponRepository;

    Coupon basicCoupon;

    @BeforeAll
    void setup() {
        basicCoupon =
                BasicCoupon.builder()
                        .id(1)
                        .name("BasicCoupon")
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();
    }

    @Test
    @Order(1)
    @Rollback(false)
    @DisplayName("베이직쿠폰이 입력되어야한다")
    public void basicCoupon_should_be_inserted() {
        Coupon savedCoupon = couponRepository.save(basicCoupon);
        assertThat(basicCoupon).isNotNull().isEqualTo(savedCoupon);
    }

    @Test
    @Order(2)
    @Rollback(false)
    @DisplayName("베이직쿠폰이 조회가 성공해야한다")
    public void basicCoupon_should_be_found() {
        Coupon coupon =
                couponRepository
                        .findById(1)
                        .orElseThrow(() -> new CouponNotFoundException("쿠폰이 존재하지 않습니다."));
        assertThat(coupon).isEqualTo(basicCoupon);
    }

    @Test
    @Order(3)
    @Rollback(false)
    @DisplayName("베이직쿠폰이 삭제되어야한다")
    public void basicCoupon_should_be_deleted() {
        couponRepository.delete(basicCoupon);
        Optional<Coupon> coupon = couponRepository.findById(1);
        assertThat(coupon.isEmpty()).isTrue();
    }

    @Test
    @Order(4)
    @Rollback(false)
    @DisplayName("베이직쿠폰이 조회 시 예외가 발생해야한다")
    public void basicCoupon_should_not_be_found() {
        assertThrows(
                EntityNotFoundException.class,
                () -> {
                    Coupon deletedCoupon = couponRepository.getById(basicCoupon.getId());
                    assertThat(deletedCoupon).isNull();
                });
    }
}

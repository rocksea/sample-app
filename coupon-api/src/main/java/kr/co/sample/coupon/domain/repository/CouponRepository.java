/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sample.coupon.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {}

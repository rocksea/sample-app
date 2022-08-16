/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.repository;

import org.springframework.data.repository.CrudRepository;

import kr.co.sample.member.domain.entity.MemberCoupon;

public interface MemberCouponRepository extends CrudRepository<MemberCoupon, Integer> {}

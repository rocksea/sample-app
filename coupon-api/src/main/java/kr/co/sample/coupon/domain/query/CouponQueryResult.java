/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query;

import kr.co.sample.coupon.domain.vo.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CouponQueryResult {
    Integer id;
    String name;
    Member member;
}

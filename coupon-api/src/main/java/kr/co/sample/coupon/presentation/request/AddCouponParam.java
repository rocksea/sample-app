/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation.request;

import javax.validation.constraints.NotNull;

import kr.co.sample.coupon.domain.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCouponParam {
    @NotNull private String name;
    @NotNull private Member member;
}

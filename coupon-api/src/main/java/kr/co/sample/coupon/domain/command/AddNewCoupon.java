/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.command;

import kr.co.sample.core.cqrs.command.Command;
import kr.co.sample.coupon.domain.vo.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddNewCoupon implements Command {
    private Integer id;
    private String name;
    private Member member;
}

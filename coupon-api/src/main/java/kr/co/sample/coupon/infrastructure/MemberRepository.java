/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.sample.coupon.domain.vo.Member;

@FeignClient(name = "member", url = "${service.member.url}")
public interface MemberRepository {
    @GetMapping("/member/v1/members/{id}")
    Member getMember(@PathVariable(name = "id") Integer id);
}

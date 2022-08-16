/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation.http;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.sample.coupon.domain.repository.MemberRepository;
import kr.co.sample.coupon.domain.vo.Member;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member/v1/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestMemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(memberRepository.getMember(id));
    }
}

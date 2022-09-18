/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.presentation.http;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sample.member.domain.query.MemberQuery;
import kr.co.sample.member.domain.query.MemberQueryResult;

@RestController
@RequestMapping(value = "/member/v1/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetMemberController {
    private final MemberQuery memberQuery;

    public GetMemberController(MemberQuery memberQuery) {
        this.memberQuery = memberQuery;
    }

    @GetMapping
    public String getHello() {
        return "Hello world";
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberQueryResult> getMember(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(memberQuery.getMemberById(id));
    }
}

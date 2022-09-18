/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.query;

import org.springframework.stereotype.Service;

import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.query.exception.MemberNotFoundException;
import kr.co.sample.member.domain.repository.MemberReadRepository;

@Service
public class MemberQuery {
    private final MemberReadRepository memberReadRepository;

    public MemberQuery(MemberReadRepository memberReadRepository) {
        this.memberReadRepository = memberReadRepository;
    }

    public MemberQueryResult getMemberById(Integer id) {
        Member member =
                memberReadRepository
                        .findMemberById(id)
                        .orElseThrow(() -> new MemberNotFoundException("멤버가 존재하지 않습니다."));
        MemberQueryResult memberQueryResult =
                MemberQueryResult.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .age(member.getAge())
                        .build();
        return memberQueryResult;
    }
}

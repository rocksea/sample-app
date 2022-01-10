/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.query;

import org.springframework.stereotype.Service;

import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberNewRepository;

@Service
public class MemberNewQuery {
    private final MemberNewRepository memberNewRepository;

    public MemberNewQuery(MemberNewRepository memberNewRepository) {
        this.memberNewRepository = memberNewRepository;
    }

    public MemberQueryResult getMemberById(Integer id) {
        Member member = memberNewRepository.findMemberById(id);
        MemberQueryResult memberQueryResult =
                MemberQueryResult.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .age(member.getAge())
                        .build();
        return memberQueryResult;
    }
}

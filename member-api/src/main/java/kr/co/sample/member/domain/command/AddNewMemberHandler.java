/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.command;

import org.springframework.stereotype.Component;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberRepository;

@Component
public class AddNewMemberHandler implements CommandHandler<AddNewMember> {
    private final MemberRepository memberRepository;

    public AddNewMemberHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void handle(AddNewMember command) {
        Member member = Member.builder().name(command.getName()).age(command.getAge()).build();
        memberRepository.save(member);
    }
}

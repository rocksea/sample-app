/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.command;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddNewMemberHandler implements CommandHandler<AddNewMember> {
    private final MemberRepository memberRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private String topic = "issue_coupon";

    @Override
    public void handle(AddNewMember command) {
        Member member = Member.builder().name(command.getName()).age(command.getAge()).build();
        memberRepository.save(member);
        System.out.printf("Produce message : %s%n", "test");
        this.kafkaTemplate.send(topic, "test");
    }
}

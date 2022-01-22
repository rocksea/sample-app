/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.command;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.member.application.AddNewMemberHandler;
import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberRepository;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AddNewMemberCommand Tests")
public class AddNewMemberCommandTest {
    @Mock private MemberRepository memberRepository;
    @Mock private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    @DisplayName("AddNewMember should be succeeded.")
    public void addNewMember_should_be_succeeded() {
        when(memberRepository.save(any(Member.class))).thenReturn(any(Member.class));
        CommandHandler<AddNewMember> addNewMemberCommandHandler =
                new AddNewMemberHandler(memberRepository, kafkaTemplate);
        AddNewMember addNewMember = AddNewMember.builder().id(1).name("rocksea").age(19).build();
        addNewMemberCommandHandler.handle(addNewMember);

        verify(memberRepository, times(1)).save(any(Member.class));
    }
}

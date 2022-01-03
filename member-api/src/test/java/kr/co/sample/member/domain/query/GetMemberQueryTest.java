/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberRepository;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GetMemberCommand Tests")
public class GetMemberQueryTest {
    @Mock private MemberRepository memberRepository;

    @Test
    @DisplayName("getMember should be succeeded.")
    public void getMember_should_be_succeeded() {
        Member member = Member.builder().id(1).name("rocksea").age(19).build();
        when(memberRepository.findById(anyInt())).thenReturn(Optional.of(member));
        MemberQuery memberQuery = new MemberQuery(memberRepository);
        MemberQueryResult memberQueryResult = memberQuery.getMemberById(1);

        assertThat(memberQueryResult.getId()).isEqualTo(member.getId());
        assertThat(memberQueryResult.getName()).isEqualTo(member.getName());
        assertThat(memberQueryResult.getAge()).isEqualTo(member.getAge());
        verify(memberRepository, times(1)).findById(anyInt());
    }
}

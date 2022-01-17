/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.sample.member.domain.Member;

@DataJpaTest
@EnableJpaRepositories(basePackages = {"kr.co.sample.coupon.*"})
@EntityScan("kr.co.sample.coupon")
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("CouponRepository Tests")
public class MemberRepositoryTest {
    @Autowired private MemberRepository memberRepository;

    Member member;

    @BeforeAll
    void setup() {
        member = Member.builder().name("Rocksea").age(20).build();
    }

    @Test
    @Rollback(false)
    @DisplayName("멤버가 입력되어야한다")
    public void member_should_be_inserted() {
        Member savedMember = memberRepository.save(member);
        assertThat(member).isNotNull().isEqualTo(savedMember);
    }

    @Test
    @Rollback(false)
    @DisplayName("멤버가 삭제되어야한다")
    public void member_should_be_deleted() {
        memberRepository.delete(member);
    }

    @Test
    @DisplayName("멤버조회 시 예외가 발생해야한다")
    public void member_should_not_be_found() {
        memberRepository.delete(member);
        assertThrows(
                InvalidDataAccessApiUsageException.class,
                () -> {
                    Member deleteMember = memberRepository.getById(member.getId());
                    assertThat(deleteMember).isNull();
                });
    }
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import feign.FeignException;
import kr.co.sample.coupon.domain.vo.Member;

@WebMvcTest
@Import({FeignAutoConfiguration.class})
@EnableFeignClients(basePackages = "kr.co.sample.coupon.infrastructure")
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs")
@ActiveProfiles("test")
@DisplayName("MemberRepository Tests")
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회에 성공해야한다")
    public void member_should_be_found() throws Exception {
        Member member = memberRepository.getMember(1);
        assertThat(member.getName()).isEqualTo("Steve");
        assertThat(member.getAge()).isEqualTo(30);
        System.out.println(member);
    }

    @Test
    @DisplayName("회원 조회 시 NotFound 예외가 발생해야 한다")
    public void member_should_not_be_found() throws Exception {
        assertThrows(
                FeignException.NotFound.class,
                () -> {
                    Member member = memberRepository.getMember(2);
                });
    }
}

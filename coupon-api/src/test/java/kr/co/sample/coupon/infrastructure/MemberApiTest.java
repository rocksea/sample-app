/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs")
@ActiveProfiles("test")
public class MemberApiTest {
    @Autowired MemberRepository memberRepository;

    @Test
    public void should_get_member() throws Exception {
        Member member = memberRepository.getMember(1);
        assertThat(member.getName()).isEqualTo("Steve");
        assertThat(member.getAge()).isEqualTo(30);
        System.out.println(member);
    }

    @Test
    public void should_get_member_notfound_exception() throws Exception {
        assertThrows(
                FeignException.NotFound.class,
                () -> {
                    Member member = memberRepository.getMember(2);
                });
    }
}

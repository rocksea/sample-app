/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.sample.coupon.domain.query.CouponQueryResult;
import kr.co.sample.coupon.domain.vo.CouponType;
import kr.co.sample.coupon.domain.vo.DiscountType;
import kr.co.sample.coupon.domain.vo.Member;
import kr.co.sample.coupon.presentation.http.request.AddCouponParam;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ComponentScan("kr.co.sample.coupon")
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs/**/*.json")
public class CouponRegistrationTest {
    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void shouldBeAddedCouponAndThenGetMember() throws Exception {
        AddCouponParam addCouponParam =
                AddCouponParam.builder()
                        .name("basic coupon")
                        .couponType(CouponType.BASIC)
                        .discountType(DiscountType.AMOUNT)
                        .amount(1000)
                        .build();

        // Add a Basic Coupon to H2
        ResponseEntity<Void> postResult =
                this.restTemplate.postForEntity(
                        String.format("http://localhost:%d/coupon", port), addCouponParam, Void.class);
        assertThat(postResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Get a Added Coupon from H2
        ResponseEntity<CouponQueryResult> getResult =
                this.restTemplate.getForEntity(
                        String.format("http://localhost:%d/coupon/1", port), CouponQueryResult.class);
        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("### coupon result : " + getResult.getBody().getName());

        // Get a member from WireMock
        ResponseEntity<Member> getMemberResult =
                this.restTemplate.getForEntity(
                        String.format("http://localhost:%d/member/v1/members/1", port), Member.class);
        assertThat(getMemberResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("### member result : " + getMemberResult.getBody().getName());
    }
}

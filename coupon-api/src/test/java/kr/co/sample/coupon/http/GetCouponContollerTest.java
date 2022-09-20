/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.http;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.sample.coupon.application.CouponQueryService;
import kr.co.sample.coupon.domain.query.CouponQueryResult;
import kr.co.sample.coupon.domain.query.exception.CouponNotFoundException;
import kr.co.sample.coupon.presentation.http.GetCouponController;
import kr.co.sample.coupon.presentation.http.exception.CouponNotFoundExceptionHandler;

@WebMvcTest
@Import({GetCouponController.class, CouponNotFoundExceptionHandler.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GetCouponController Tests")
class GetCouponContollerTest {
    @Autowired private MockMvc mockMvc;

    // @Autowired private WebApplicationContext context;

    @MockBean private CouponQueryService couponQuery;

    @Test
    @WithMockUser
    @DisplayName("Coupon should be found.")
    void coupon_should_be_found() throws Exception {
        Integer couponId = 1;
        CouponQueryResult couponQueryResult =
                CouponQueryResult.builder().id(couponId).name("rocksea").build();
        when(couponQuery.getCouponById(couponId)).thenReturn(couponQueryResult);
        this.mockMvc
                .perform(
                        get("/coupon/v1/coupons/" + couponId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("Coupon should not be found.")
    void get_coupon_should_be_failed() throws Exception {
        Integer couponId = 100;
        when(couponQuery.getCouponById(couponId)).thenThrow(CouponNotFoundException.class);
        this.mockMvc
                .perform(
                        get("/coupon/v1/coupons/" + couponId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

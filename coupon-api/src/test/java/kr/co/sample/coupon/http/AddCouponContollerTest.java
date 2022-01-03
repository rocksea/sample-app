/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.http;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.coupon.domain.command.AddNewCoupon;
import kr.co.sample.coupon.presentation.AddCouponController;

@WebMvcTest
@Import(AddCouponController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AddCouponController Tests")
class AddCouponContollerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private CommandHandler<AddNewCoupon> commandHandler;

    @Test
    @WithMockUser
    @DisplayName("Coupon should be created.")
    void coupon_should_be_created() throws Exception {
        this.mockMvc
                .perform(
                        post("/coupon")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(
                                        "{\"name\":\"rocksea's coupon\", \"member\":{\"name\":\"rocksea\", \"age\": 19 }}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    @DisplayName("AddCoupon should be failed.")
    void add_coupon_should_be_failed() throws Exception {
        this.mockMvc
                .perform(post("/coupon").contentType(MediaType.APPLICATION_JSON).with(csrf()).content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.presentation.http;

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
import org.springframework.web.context.WebApplicationContext;

import kr.co.sample.member.domain.query.MemberQuery;
import kr.co.sample.member.domain.query.MemberQueryResult;
import kr.co.sample.member.domain.query.exception.MemberNotFoundException;
import kr.co.sample.member.domain.query.exception.MemberNotFoundExceptionHandler;

@WebMvcTest
@Import({GetMemberController.class, MemberNotFoundExceptionHandler.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GetMemberController Tests")
class GetMemberContollerTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private WebApplicationContext context;

    @MockBean private MemberQuery memberQuery;

    @Test
    @WithMockUser
    @DisplayName("Member should be found.")
    void member_should_be_found() throws Exception {
        Integer memberId = 1;
        MemberQueryResult memberQueryResult =
                MemberQueryResult.builder().id(memberId).name("rocksea").age(99).build();
        when(memberQuery.getMemberById(memberId)).thenReturn(memberQueryResult);
        this.mockMvc
                .perform(get("/member/" + memberId).contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("Member should not be found.")
    void get_member_should_be_failed() throws Exception {
        Integer memberId = 100;
        when(memberQuery.getMemberById(memberId)).thenThrow(MemberNotFoundException.class);
        this.mockMvc
                .perform(
                        get("/member/" + memberId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

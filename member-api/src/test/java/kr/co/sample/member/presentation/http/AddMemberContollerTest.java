/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.presentation.http;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.member.domain.command.AddNewMember;

@WebMvcTest
@Import(AddMemberController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AddMemberController Tests")
class AddMemberContollerTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private WebApplicationContext context;

    @MockBean private CommandHandler<AddNewMember> commandHandler;

    @Test
    @WithMockUser
    @DisplayName("Member should be created.")
    void member_should_be_created() throws Exception {
        this.mockMvc
                .perform(
                        post("/member")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content("{\"name\":\"rocksea\", \"age\":20}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    @DisplayName("AddMember should be failed.")
    void add_member_should_be_failed() throws Exception {
        this.mockMvc
                .perform(post("/member").contentType(MediaType.APPLICATION_JSON).with(csrf()).content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

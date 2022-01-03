/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.presentation.http;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import kr.co.sample.core.cqrs.command.CommandHandler;
import kr.co.sample.member.domain.command.AddNewMember;
import kr.co.sample.member.presentation.http.request.AddMemberParam;

@RestController
@RequestMapping(value = "/member")
public class AddMemberController {
    private final CommandHandler<AddNewMember> addNewMemberCommandHandler;

    public AddMemberController(CommandHandler<AddNewMember> addNewMemberCommandHandler) {
        this.addNewMemberCommandHandler = addNewMemberCommandHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMember(@Valid @RequestBody AddMemberParam memberParam) {
        AddNewMember addNewMember =
                AddNewMember.builder().name(memberParam.getName()).age(memberParam.getAge()).build();
        addNewMemberCommandHandler.handle(addNewMember);
    }
}

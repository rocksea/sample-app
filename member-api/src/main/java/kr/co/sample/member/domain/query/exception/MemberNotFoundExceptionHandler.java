/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.query.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberNotFoundExceptionHandler {
    @ExceptionHandler({MemberNotFoundException.class})
    public ResponseEntity<String> memberNotFoundException(MemberNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

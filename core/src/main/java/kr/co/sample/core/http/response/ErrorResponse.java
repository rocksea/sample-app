/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.core.http.response;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {

    private int status;
    private List<String> errors;
    private String path;

    private LocalDateTime timestamp;

    public ErrorResponse(int status, LocalDateTime timestamp, String path, List<String> errors) {
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        this.errors = errors;
    }

    public ErrorResponse(int status, LocalDateTime timestamp, String path, String error) {
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        errors = Collections.singletonList(error);
    }
}

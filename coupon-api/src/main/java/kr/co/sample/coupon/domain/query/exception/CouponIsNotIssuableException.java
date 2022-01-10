/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.query.exception;

import kr.co.sample.core.exception.NotFoundException;

public class CouponIsNotIssuableException extends NotFoundException {
    public CouponIsNotIssuableException() {
        super();
    }

    public CouponIsNotIssuableException(String message) {
        super(message);
    }

    public CouponIsNotIssuableException(String message, Throwable cause) {
        super(message, cause);
    }
}

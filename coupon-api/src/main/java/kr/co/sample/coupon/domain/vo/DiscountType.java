/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.vo;

import lombok.NonNull;

public enum DiscountType {
    AMOUNT() {
        public Integer getDiscountAmount(@NonNull Integer price, @NonNull Integer discountAmount) {
            return price - discountAmount;
        }
    },
    RATE() {
        public Integer getDiscountAmout(@NonNull Integer price, @NonNull Integer discountAmount) {
            return price - discountAmount;
        }
    }
}

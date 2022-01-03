/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sample.coupon.domain.query.CouponQuery;
import kr.co.sample.coupon.domain.query.CouponQueryResult;

@RestController
@RequestMapping(value = "/coupon", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetCouponController {
    private final CouponQuery couponQuery;

    public GetCouponController(CouponQuery couponQuery) {
        this.couponQuery = couponQuery;
    }

    @GetMapping
    public String getHello() {
        return "Hello world";
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponQueryResult> getMember(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(couponQuery.getCouponById(id));
    }
}

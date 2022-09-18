/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.presentation.http;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.sample.coupon.application.CouponQueryService;
import kr.co.sample.coupon.domain.query.CouponQueryResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/coupon/v1/coupons", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetCouponController {
    private final CouponQueryService couponQuery;

    @GetMapping
    public String getHello() {
        return "Hello world";
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponQueryResult> getCoupon(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(couponQuery.getCouponById(id));
    }

    @GetMapping("/{id}/issueable")
    public ResponseEntity<CouponQueryResult> getIsCouponIssuableByMember(
            @PathVariable("id") Integer couponId, @RequestParam("memberId") Integer memberId) {
        return ResponseEntity.ok()
                .body(couponQuery.getCouponIssuableByCouponIdAndMemberId(couponId, memberId));
    }
}

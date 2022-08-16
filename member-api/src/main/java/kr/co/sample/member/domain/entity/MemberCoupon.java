/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.entity;

import javax.persistence.*;

import org.springframework.data.redis.core.RedisHash;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Entity
@RedisHash("member_coupon")
public class MemberCoupon {
    @Id private Integer id;
    private String couponType;
    private String name;
    private String discountType;
    private Short rate;
    private Integer amount;
}

/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.entity;

import javax.persistence.*;

import kr.co.sample.coupon.domain.Coupon;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "coupon_member")
public class CouponMember {
    @Id @GeneratedValue private Integer id;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column private Integer memberId;
    @Column private String name;
    @Column private Integer age;
}

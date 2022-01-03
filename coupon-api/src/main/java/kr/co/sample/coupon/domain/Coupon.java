/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain;

import javax.persistence.*;

import kr.co.sample.coupon.domain.entity.CouponMember;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id @GeneratedValue private Integer id;

    @Column private String name;

    @OneToOne(mappedBy = "coupon", cascade = CascadeType.ALL)
    private CouponMember couponMember;

    void setCouponMember(CouponMember couponMember) {
        this.couponMember = couponMember;
    }
}

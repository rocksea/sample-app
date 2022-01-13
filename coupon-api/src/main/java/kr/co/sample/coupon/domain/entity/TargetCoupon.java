/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.coupon.domain.entity;

import java.util.Set;

import javax.persistence.*;

import kr.co.sample.coupon.domain.aggregate.Coupon;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Entity
@DiscriminatorValue("T")
public class TargetCoupon extends Coupon {
    @ElementCollection
    @CollectionTable(name = "coupon_target_members", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "member_id")
    private Set<Integer> targetMemberIds;

    public TargetCoupon() {}

    public boolean isIssuable(Integer memberId) {
        if (targetMemberIds == null || targetMemberIds.isEmpty()) return false;
        return targetMemberIds.contains(memberId);
    }
}

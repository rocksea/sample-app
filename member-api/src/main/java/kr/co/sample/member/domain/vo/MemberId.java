/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.*;

@EqualsAndHashCode
@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MemberId implements Serializable {
    private Integer id;

    public static MemberId of(Integer id) {
        return new MemberId(id);
    }
}

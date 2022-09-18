/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.repository;

import java.util.Optional;

import kr.co.sample.member.domain.Member;

public interface MemberReadRepository {
    Optional<Member> findMemberById(Integer memberId);
}

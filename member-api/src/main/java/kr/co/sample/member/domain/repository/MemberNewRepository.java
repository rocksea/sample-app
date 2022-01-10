/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.domain.repository;

import static kr.co.sample.member.domain.QMember.member;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.sample.member.domain.Member;

@Repository
public class MemberNewRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberNewRepository(JPAQueryFactory jpaQueryFactory) {
        super(MemberNewRepository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Member findMemberById(Integer id) {
        return jpaQueryFactory.selectFrom(member).where(member.id.eq(id)).fetchOne();
    }
}

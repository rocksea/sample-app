/*
 * Copyright 2021 ROCKSEA. All rights Reserved.
 * ROCKSEA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.co.sample.member.infrastructure;

import static kr.co.sample.member.domain.QMember.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.sample.member.domain.Member;
import kr.co.sample.member.domain.repository.MemberReadRepository;

@Repository
public class MemberQueryDslRepository extends QuerydslRepositorySupport
        implements MemberReadRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberQueryDslRepository(JPAQueryFactory jpaQueryFactory) {
        super(MemberQueryDslRepository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<Member> findMemberById(Integer id) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(member).where(member.id.eq(id)).fetchOne());
    }
}

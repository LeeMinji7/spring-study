package com.example.member.repository;

import com.example.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
// 해당 인터페이스가 JpaRepository를 상속받으면, 구현체를 자동으로 만들어 스프링 빈에 자동으로 등록함

    // findBy()의 규칙을 통해 ()를 조회하는 JPQL를 제공하고 SQL로 번역됨
    // JPQL : select m from Member m where m.name=?
    @Override
    Optional<Member> findByName(String name);
}

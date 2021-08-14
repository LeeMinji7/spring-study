package com.example.member.repository;

import com.example.member.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 EntityManager로 모든 동작 수행함
    // build.gradle 에서 jpa 라이브러리 설정하면, 스프링부트가 자동으로 db에 연결해서 EntityManager 생성해줌
    // 만든것을 DI 받으면 됨

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // 이렇게 만들면, jpa가 쿼리만들어서 db에 집어넣고 setId까지 모든 것을 실행
        em.persist(member); // persist : 영구저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // em.find(조회할 타입, PK)하면 조회됨
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 위에 id는 pk이니까 find쓰는데, name은 아니므로 아래와 같이 특별한 jpql 객체지향 쿼리언어 사용해야 함
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql : 테이블이 아닌 객체(엔티티)를 대상으로 쿼리를 날리면, sql로 번역이 됨
        // select 할 때 객체 자체를 조회 ( id, name 이런식 아님)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}

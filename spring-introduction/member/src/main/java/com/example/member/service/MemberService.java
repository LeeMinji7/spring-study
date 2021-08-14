package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional  // jpa 사용하려면, 데이터를 저장하고 변경하는 곳에 해당 애노테이션 필요
                // jpa는 모든 데이터 변경이 transaction 안에서 실행되어야 함
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    public Long join(Member member){

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증
        // 같은 이름이 있는 중복 회원X
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {         // null 값이 아닌 존재하는 값이 있을 경우
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        // 위보다 이런 형태로 만들면 간단하게 완성!!
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {         // null 값이 아닌 존재하는 값이 있을 경우
                 throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

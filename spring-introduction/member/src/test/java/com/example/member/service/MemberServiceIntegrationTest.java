package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프릥이 테스트 할 때 사용, 진짜 스프링을 띄워서 테스트 실행
@Transactional    // 동작 검증 후에 롤백해서 db에 데이터 반영되지 않게 하는 것
                    /* 테스트에 붙이면, 테스트 실행전에 트랜잭션을 실행하고 db에 데이터를 넣은 후에
                    테스트 끝나면 롤백하여 db에 데이터 반영 안됨 */
class MemberServiceIntegrationTest {    // 여러 리포지토리 통합 테스트 가능

    @Autowired  // 테스트는 편한 방식으로 진행해도 되므로 -> 필드 주입
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {   // test는 이름 한글로 변경 가능
        //given : 이런 상황이 주어졌을 때
        Member member = new Member();
        member.setName("spring1");

        //when : 이것을 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과가 이렇게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // try-catch 같은 예외처리 간단하게 하는 방법
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
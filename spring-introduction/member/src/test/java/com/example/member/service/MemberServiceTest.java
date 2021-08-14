package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;

    // service에서의 리포지토리와 test에서의 리포지토리가 같아야 하는데 같을까? No
    // ==> DI(의존성 주입) -> @BeforeEach 참고
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 전에 실행
    public void beforeEach(){
        /* 의존성주입(DI)
         * : MemberService 클래스가 직접 리파지토리를 생성하지 않고 외부에서 넣어준주는 것*/
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void clear(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   // test는 이름 한글로 변경 가능
        //given : 이런 상황이 주어졌을 때
        Member member = new Member();
        member.setName("hello");

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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
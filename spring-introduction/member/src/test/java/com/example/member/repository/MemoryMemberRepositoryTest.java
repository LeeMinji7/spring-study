package com.example.member.repository;

import com.example.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /* 모든 테스트 한번에 돌리면 오류 발생 => findByName()오류 */
// -> why?
// -> 테스트 순서는 랜덤이며, 메소드 별로 따로 따로 동작
// -> findAll()이 test 되어 지면서 만든 객체들을 findByName()에서 다시 사용되어 객체가 같지 않아 오류
// ==> [해결법] : 한 메소드의 test가 끝난 후에, 데이터를 clear를 해줘야 함  ==> @afterEach를 통해 데이터 지워주는 콜백함수 적용

    @AfterEach      // 한 메소드의 테스터 끝날 때마다 해당 메소드 실행되어 데이터를 지워주는 역할, 콜백함수
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // 검증, 잘 되었는지 확인
//        System.out.println("result = " + (result == member)); // 두 값 비교 (1)
//        Assertions.assertEquals(member, result);    // (기대값, 실제값) 두 값 비교 (2)
                                                        // 출력되는 값은 없지만 두 값이 다르면 오류발생
        assertThat(result).isEqualTo(member); // 두 값 비교 (3) // 다르면 오류발생
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}

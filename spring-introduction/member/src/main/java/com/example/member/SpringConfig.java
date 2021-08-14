package com.example.member;

import com.example.member.repository.MemberRepository;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

   /* // 스프링이 설정파일(application.properties)를 보고 자체적으로 DataSource를 생성하고 스프링 빈으로 만듦 -> DI 가능
    private DataSource dataSource;  // 데이터베이스 커넥션을 획득할 때 사용하는 객체

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired  // JpaRepository가 인터페이스 구현체를 만들고 스프링 빈에 등록 -> 주입 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean   // 서비스 스프링 빈 등록
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /*@Bean   // 시간 측정 로직 AOP 스프링 빈 등록 => @Component 방법1로 빈 등록할 거임
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}

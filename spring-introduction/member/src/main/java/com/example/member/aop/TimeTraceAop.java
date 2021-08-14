package com.example.member.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect     // AOP 사용 시 필요한 애노테이션
@Component  // 스프링 빈 등록하기 위한 방법 1 -> 방법 2는 SpringConfig에서 직접 빈 들록
public class TimeTraceAop {

    // 공통 관심 사항 타켓팅, 적용대상 선택
    // if)서비스 하위만 적용 => execution(* com.example.member.service..*(..))"
    @Around("execution(* com.example.member..*(..))")   // 패키지 하위에 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
    /* joinPoint => 호출 될 때마다 해당 지점에서의 action을 지정해줄 수 있음*/

        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            return joinPoint.proceed(); // 다음 메소드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

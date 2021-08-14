package com.example.member.controller;


import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너에 MemberController 객체를 생성하여 넣어두며, 스프링이 관리
public class MemberController {

    /* 이러한 객체 생성은 다른 컨트롤러에서도 사용 가능하므로 좋은 방법x, 하나만 생성해서 공유하는 것이 좋음
    * => @Autowired : 하나만 등록이 가능한 스프링 컨테이너에 등록한 후 공유 */
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    @Autowired  // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다 => DI(의존성주입)
    public MemberController(MemberService memberService) {  // 스프링이 스프링 컨테이너에 있는 멤버서비스를 가지고 와서 연결
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());  // 멤버서비스 클래스 확인 => 프록시
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/" ;   // 회원 가입이 끝나면 홈 화면으로 보내는 것
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

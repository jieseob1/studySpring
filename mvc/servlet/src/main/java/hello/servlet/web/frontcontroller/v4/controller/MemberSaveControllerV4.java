package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) { // 이부분 잘 이해가 안됨 paramMap자체는 httpServletRequest 대용으로 사용하는 것
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
//        ModelView mv = new ModelView("save-result");
//        mv.getModel().put("member", member); // 필요한 모델 객체 넣어서 반환하면 끝난다.
        //return mv;
        return "save-result";
    } //회원 저장

}

package com.basic.springbasic;

import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberService;
import com.basic.springbasic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "spring1", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}

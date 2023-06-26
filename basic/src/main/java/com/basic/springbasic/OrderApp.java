package com.basic.springbasic;

import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberService;
import com.basic.springbasic.member.MemberServiceImpl;
import com.basic.springbasic.order.Order;
import com.basic.springbasic.order.OrderService;
import com.basic.springbasic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); //회원 가입기능

        Order order = orderService.createOrder(memberId,"itemA", 10000);

        System.out.println("order=" + order.toString());
    }
}

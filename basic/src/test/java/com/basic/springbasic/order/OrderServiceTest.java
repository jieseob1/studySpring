package com.basic.springbasic.order;

import com.basic.springbasic.AppConfig;
import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberService;
import com.basic.springbasic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    //회원 서비스와(가입 및 회원 조회) ,주문 서비스(주문 생성)에 대해서 인스턴스 생성
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();

    }
    @Test
//    createOrder(Long memberId, String itemName, int itemPrice)
    void createOrder() {

        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //orderServiceImpl에서 createOrder를 보게 되면, order를 return 해준다.

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }


}
package com.basic.springbasic.order;

import com.basic.springbasic.discount.FixDiscountPolicy;
import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy()); //생성자 -> 필수값 인지 가능
        Order order = orderService.createOrder(1L, "itemA", 10000);

    }
}
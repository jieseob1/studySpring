package com.basic.springbasic.order;

import com.basic.springbasic.discount.DiscountPolicy;
import com.basic.springbasic.discount.FixDiscountPolicy;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    //회원 찾을 거
    private final MemberRepository memberRepository = new MemoryMemberRepository(); //왜 private final로 들어가지?

    //고정 할인 정책
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}

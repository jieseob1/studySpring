package com.basic.springbasic.discount;

import com.basic.springbasic.annotation.MainDiscountPolicy;
import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy //qualifier대신에 이렇게 Qualifier 자체를 만들어서 사용
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10; //10프로 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

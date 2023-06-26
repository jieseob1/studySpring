package com.basic.springbasic.discount;

import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10% 할인이 적용되어야 한다.")
    void vip_o() { //vip인 경우

        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("BASIC은 할인 적용이 되지 않아야 한다.")
    void vip_x() { //vip가 아닌 grade.basic인 경우

        //given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
}
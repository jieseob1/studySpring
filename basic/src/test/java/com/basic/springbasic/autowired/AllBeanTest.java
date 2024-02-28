package com.basic.springbasic.autowired;

import com.basic.springbasic.AutoAppConfig;
import com.basic.springbasic.discount.DiscountPolicy;
import com.basic.springbasic.member.Grade;
import com.basic.springbasic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class); //bean으로 등록
        //둘다 등록, 자동 의존 관계 주입이 될 때, autoappconfig에서는 컴포넌트 스캔 => fix,rate 둘다 스프링 빈에 등록된다.

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }

    static class  DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;


        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);

        }
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode); // 근데 어떻게 맞춰서 나오는지가 신기함
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }
}

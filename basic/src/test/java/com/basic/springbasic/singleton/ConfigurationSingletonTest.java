package com.basic.springbasic.singleton;

import com.basic.springbasic.AppConfig;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemberServiceImpl;
import com.basic.springbasic.order.OrderService;
import com.basic.springbasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository); // 3개 다 같음을 확인할 수 있다.
        assertThat(memberRepository1).isEqualTo(memberRepository2);

    }

//    @Test
//    void configurationDeep() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        AppConfig bean = ac.getBean(AppConfig.class);
//
//        System.out.println("bean.getClass() = " + bean.getClass());
//
//    }
}

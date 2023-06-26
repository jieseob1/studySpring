package com.basic.springbasic;

import com.basic.springbasic.discount.DiscountPolicy;
import com.basic.springbasic.discount.FixDiscountPolicy;
import com.basic.springbasic.discount.RateDiscountPolicy;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemberService;
import com.basic.springbasic.member.MemberServiceImpl;
import com.basic.springbasic.member.MemoryMemberRepository;
import com.basic.springbasic.order.OrderService;
import com.basic.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//구성 정보 => application 설정 정보
public class AppConfig {

    //리팩토링을 진행한 코드 => 원래는 중복이 발생했었다. 파라미터로 return new 즉, 갓 생성한 인스턴스를 넣어줬었다.
    @Bean
    // bean 어노테이션을 사용하게 되면, 스프링 컨테이너에 이 모든 것들이 빈으로 등록이 된다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //각 클래스에 생성자가 없어서 문제가 생기는 부분
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

package com.basic.springbasic.beanFind;

import com.basic.springbasic.discount.DiscountPolicy;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    // AnnotationConfigApplicationContext의 파라미터로써, SameBeanConfig 즉, 설정 파일을 넣어줘야 한다.

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
//    expected single matching bean but found 2
    void findBeanByTypeDuplicate() {
//        MemberRepository bean = ac.getBean(MemberRepository.class); // 이렇게 하면 터지게 된다.
        //타입만 지정 => 꺼낼때 타입만 꺼낼껀데, 해당 부분이 문제가 된다.
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class)
        ); //해당 람다 부분에  ac.getBean(MemberRepository.class)가 들어가게 된다.
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정해주면 된다.")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }@Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() { //타입으로 모두 조회하는 방법
        // 나중에 이렇게 확인 하는 방법도 @Autowired를 자동 의존 관계 젹용할때도 자동적용된다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key" + key + "value" + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType); //통으로 출력
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2); //2개 등록 되어 있으니까
    }

    @Configuration
    static class SameBeanConfig { // discountPolicy를 사용하려면 appConfig에다가 다시 새로 만들어야 하므로, 따로 여기서 쓸
        //configuration파일을 만들어준다.
        //static => 클래스 안에서 클래스를 사용한다는 것은 스코프를 ApplicationContextSameBe~~ 안에서만 사용하겠다라는 의미
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }

}

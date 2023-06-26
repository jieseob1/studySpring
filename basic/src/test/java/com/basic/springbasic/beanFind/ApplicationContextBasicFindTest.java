package com.basic.springbasic.beanFind;

import com.basic.springbasic.AppConfig;
import com.basic.springbasic.member.MemberService;
import com.basic.springbasic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isEqualTo(MemberServiceImpl.class);

//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("bean = " + beanDefinitionName + "object = " + bean);
//        }
    }
    @Test
    @DisplayName("이름 없이 타입만으로")
//    void findApplicationBean() {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
//
//            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
//            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
//                Object bean = ac.getBean(beanDefinitionName);
//                System.out.println("name = " + beanDefinitionName + "object = " + bean);
//            }
//        }
//    }
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isEqualTo(MemberServiceImpl.class);
    }



}

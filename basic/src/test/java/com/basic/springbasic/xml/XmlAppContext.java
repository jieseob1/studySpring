package com.basic.springbasic.xml;

import com.basic.springbasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); //test를 먼저 작성해놨기 때문에 안됨

        //appConfig.xml을 만들어줘야 한다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);//memberService bean을 꺼낼거임
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}

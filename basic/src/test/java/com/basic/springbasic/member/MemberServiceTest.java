package com.basic.springbasic.member;

import com.basic.springbasic.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        //test 실행하기 전에 먼저 실행해주는 공간
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();

    }
    @Test
    void join() {
        //given
        Member member =new Member(1L, "memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

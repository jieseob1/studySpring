package service;

import com.basic.springbasic.AppConfig;
import com.basic.springbasic.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    private MemberRepository memberRepository;
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberRepository = appConfig.memberRepository();

    }
    MemberService memberService = new MemberServiceImpl(memberRepository);


    @Test
    void join() {
//given
        Member member = new Member(1L, "memberA", Grade.VIP);
//when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
//then
        assertThat(member).isEqualTo(findMember);
    }

}

package com.basic.springbasic;

import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
//        컴포넌트 스캔으로 돌면서 자동 지정 해주는데, 그 중 뺄것을 지정하는 것
)
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository") //나중에 소문자로 바뀌므로
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

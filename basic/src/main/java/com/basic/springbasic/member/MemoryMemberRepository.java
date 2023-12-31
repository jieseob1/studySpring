package com.basic.springbasic.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//이제 빈등록을 따로 안해도 된다.
@Component
public class MemoryMemberRepository implements MemberRepository{

    public static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

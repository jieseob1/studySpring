package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository(); //싱글톤 만드는 것 => 생성자 생성 => 자기 자신의 인스턴스 생성

    public static MemberRepository getInstance() { // 메서드를 통한 단일 인스턴스의 반환 및 해당 클래스의 생성 제한
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();

    }
}

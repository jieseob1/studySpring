package hello.db1.repository;

import hello.db1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;



class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV0", 10000);
        repository.save(member);
        //findById
        Member findMember = repository.findById(member.getMemberId());
        assertThat(findMember).isEqualTo(member); //저장한거랑, 내가 아이디로 찾은 멤버랑 같은지
    }
}
package hello.db1.domain;

import lombok.Data;

@Data
public class Member {
    //member 테이블에 데이터 저장, 조회 할 때 사용한다.

    private String memberId;
    private int money;

    public Member() {
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}

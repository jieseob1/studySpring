package hello.db1.connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class DBConnectionUtilTest { //데이터베이스에 연결이 되어있는 부분 확인하는 부분
    @Test
    void connection() {
        Connection connection = DBConnectionUtil.getConnection(); // 왜 DBConnectionUtil은 내가 만들어 놓은거
        assertThat(connection).isNotNull(); //connection이 되었는지 안되어있는지 확인
    }

}
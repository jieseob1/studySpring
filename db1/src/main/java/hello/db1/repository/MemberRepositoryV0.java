package hello.db1.repository;

import hello.db1.connection.DBConnectionUtil;
import hello.db1.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {

    // Member를 저장하는 메소드
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?,?)"; //values가 memberId, money인 값을 집어넣는다.

        Connection conn = null; //이거 이써야지 연결
        PreparedStatement pstmt = null; // 준비된 statement

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql); //sql문 db로 전달
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate(); //업데이트 실행
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException { //쿼리 실행
        //sql: 데이터 조회를 위한 select SQL준비
        String sql = "select * from member where member_id= ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql); //sql을 DB로 보낼 준비
            pstmt.setString(1, memberId);

            //데이터를 변경할때 executeUpdate를 사용하지만, 데이터를 조회할때는 executeQuery사용 => executeQuery의 결과는 ResultSet 에 넣어서 반환해준다.
            rs = pstmt.executeQuery(); //쿼리 실행

            if (rs.next()) { // rs.next는 뭐지
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money")); //rs.getInt부분 잘 이해 안됨

                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw  e;
        } finally {
            close(conn, pstmt, rs);
        }

    }
    public void update(String memberId, int money) throws SQLException {
        String sql= "update member set money=? where member_id=?"; //업데이트할 열과 해당 열에 적용할 값을 지정

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate(); //update한 row의 수
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
        //
    }

    public void delete(String memberId, int money) throws SQLException{
        String sql = "delete from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.info("");
        }
        finally {
            close(conn, pstmt, null);
        }
    }
    private void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) { //결과set이 있는 경우에는 닫아준다.
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if (stmt != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}

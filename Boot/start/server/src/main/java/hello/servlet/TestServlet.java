package hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * http://localhost:8080/test
 */
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("TestServlet.service");
    resp.getWriter().println("test"); //응답에 테스트 찍는거 => 클라이언트 요청에서 서버에서 테스트로 뱉어줌
  }
}

package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  웹 브라우저 http 만들어서 던져줌
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);


        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 파싱하면 귀찮지만, servlet을 사용함으로써 파싱을 안해도 됨

        response.setContentType("text/palin");
        response.setCharacterEncoding("utf-8"); //컨텐츠 정보 =>헤더에 들어간다.
        response.getWriter().write("hello" + username); //http 메시지 바디에 들어감


    } //서블릿 호출 => 서비스 메소드 호출
}

package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {
    //여기가 핸들러 어댑터
    boolean supports(Object handler);//  처리가능한지 안한지

    //4. handler 호출
    //5. ModelView 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException;
    // 실제 컨트롤러 호출
    //그 결과로 ModelView 반환
    // 이전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만, 이 어댑터를 통해, 실제 컨트롤러가 호출된다.

}

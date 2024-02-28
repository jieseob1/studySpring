package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //기본
    //private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //여기는 아무 컨트롤러던지 다 들어갈 수 있어야한다.
    // handler 찾아서 매핑 해주는 부분
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); //handlerAdapter 리스트 => 어떤 handlerAdapter들이 있는지 조회하기 위함
    //어댑터 여러개 담겨있고 그중에 하나 가져다 써야함

    public FrontControllerServletV5() {
        initHandlerAdapters();
        initHandlerMappingMap();
    }

    //handler adapter 목록
    //2. 핸들러를 처리할 수 있는 핸들러 어댑터 조회
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter()); //handlerAdapter 리스트에 핸들러어댑터 넣는 과정
        handlerAdapters.add(new ControllerV4HandlerAdapter());
        //어댑터에
    }

    private void initHandlerMappingMap() { //handlerMappingMap put 해주는 부분 -> init handlerMappingMap
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // return MemberFormControllerV4
        Object handler = getHandler(request); //핸들러 찾아와
        //요청이 오면 핸들러매핑맵에서 핸들러를 찾는다.

        // 일단 핸들러 매칭 정보 뒤짐
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //v3나 v4냐에 따라서 처리할 수 있는 핸들러 목록을 뒤져서 핸들러를 가지고 와야한다.

        //return ControllerV4HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 핸들러 어댑터 찾아와

        //3. handle(handler)
        ModelView mv = adapter.handle(request, response, handler); //실제 컨트롤러 호출
        //modelViewe 반환

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) { //핸들러 어댑터를 탐색하면서
            if (adapter.supports(handler)) { // 만약 어댑터가 handler 를 서포트 해줄 수 있다면, adpater를 빼준다.
                return adapter; // 있는 경우, 어댑터 반환
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    // 1. 핸들러 매핑 정보 조회
    private Object getHandler(HttpServletRequest request) { //핸들러 가져오는 부분
        String requestURI = request.getRequestURI(); // uri 주소 가져오는 부분
        return handlerMappingMap.get(requestURI); //요청한 uri에 맞는 handler를 가지고 온다.
    }
}

package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap); // HttpServleRequest가 제공하는 파라미터 => 프론트 컨트롤러가 paramMap에 담아서 호출해주면 된다.

}

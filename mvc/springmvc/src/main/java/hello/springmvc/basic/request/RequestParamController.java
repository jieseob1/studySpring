package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok"); // 뭐가 다르길래 이렇ㄱ ㅔ썼지?
    }

    @ResponseBody //ok라는 문자를 그냥 body에 넣음
    @RequestMapping("/request-param-v2")
    public String requestParamV2( // controller면서 String이면, ok라는 뷰를 찾게 된다.
                                  @RequestParam("username") String memberName,
                                  @RequestParam("age") int memberAge
    ) throws IOException {
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    //modelAttribute 어노태이션을 사용하지 않는 경우,
//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        return "ok";
//    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
    //생략가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}

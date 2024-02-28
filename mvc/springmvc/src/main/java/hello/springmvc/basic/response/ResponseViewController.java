package hello.springmvc.basic.response;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    //만약 여기서 @ResponseBody를 사용했다면, 그냥 response/hello만 반환해준다.
    @ResponseBody
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) { //String 반환 => Model 필요
        model.addAttribute("data", "hello!!");
        return "response/hello"; //뷰의 논리적 이름
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }

}

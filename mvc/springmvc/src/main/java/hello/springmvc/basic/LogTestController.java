package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); //현재 나의 클래스 => Slf4j로 대체

    @RequestMapping("/log-test")
    public  String logTest() {
        String name = "Spring";

        log.trace("info log={}",name);
        log.debug("info log={}",name);
        log.info("info log={}",name);
        log.warn("info log={}",name);
        log.error("info log={}",name);

        return "ok";
    }
}

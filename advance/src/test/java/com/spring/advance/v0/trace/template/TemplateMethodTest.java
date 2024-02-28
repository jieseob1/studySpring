package com.spring.advance.v0.trace.template;

import com.spring.advance.trace.template.code.AbstractTemplate;
import com.spring.advance.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }
  private void logic1() {
    long startTime = System.currentTimeMillis();
    //execute business logic
    log.info("비즈니스 로직1 실행");
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resulttime = {}", resultTime);
  }
  private void logic2() {
    long startTime = System.currentTimeMillis();
    //execute business logic
    log.info("비즈니스 로직2 실행");
    //end business logic

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  @Test
  //이전에 여러번 바꿔야 될것을, AbstractTemplate execute 부분에서 한번만 바꾸면 된다. => 단일 책임 원칙이 잘 지켜짐
  void templateMethodV1() { //이해 안된다면 다형성 =>
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute(); // abstractTemplate에 있는 call()이 overried한 SubClassLogic1의 call()로 대체된다.

    AbstractTemplate template2 = new SubClassLogic1();
    template2.execute(); // abstractTemplate에 있는 call()이 overried한 SubClassLogic2의 call()로 대체된다.

  }

  @Test
  void templateMethodV2() {
    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직1 실행");
      }
    };
    log.info("클래스 이름1 = {}", template1.getClass());
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직2 실행");
      }
    };
    log.info("클래스 이름2 = {}", template2.getClass());
      template2.execute();
  }
}

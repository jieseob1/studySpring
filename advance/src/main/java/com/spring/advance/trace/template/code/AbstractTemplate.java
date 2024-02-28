package com.spring.advance.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
  public void execute() {
    long startTime = System.currentTimeMillis();
    //execute business logic
    call();
    //end business logic

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  protected abstract void call(); // 변하는 부분=> 상속, 오버라이딩
}

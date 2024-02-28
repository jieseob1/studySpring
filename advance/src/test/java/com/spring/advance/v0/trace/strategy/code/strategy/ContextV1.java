package com.spring.advance.v0.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

  //필드 주입
  //필드에 전략을 보관하는 방식 => 한번 저장해놓으면 계속 이걸 쓰게 된다.
  private Strategy strategy;

  public ContextV1(Strategy strategy) {
    this.strategy = strategy;
  }

  public void execute() {
    long startTime = System.currentTimeMillis();
    //execute business logic
    strategy.call();
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resulttime = {}", resultTime);
  }
}

package com.spring.advance.v0.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 받는 방식
 */
@Slf4j
public class ContextV2 {
  public void execute(Strategy strategy) {
    long startTime = System.currentTimeMillis();
    //execute business logic
    strategy.call(); // 위임
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resulttime = {}", resultTime);
  }
}
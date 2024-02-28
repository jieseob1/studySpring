package com.spring.advance.v0.trace.strategy;

import com.spring.advance.v0.trace.strategy.code.strategy.ContextV2;
import com.spring.advance.v0.trace.strategy.code.strategy.Strategy;
import com.spring.advance.v0.trace.strategy.code.strategy.StrategyLogic1;
import com.spring.advance.v0.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  /**
   * 전략패턴 사용
   */
  @Test
  void strategyV1() {
    ContextV2 context = new ContextV2();
    context.execute(new StrategyLogic1());
    context.execute(new StrategyLogic2());
  }

  /**
   * 전략패턴 익명함수
   */
  @Test
  void strategyV2() {
    ContextV2 context = new ContextV2();
    context.execute(new Strategy(){

      @Override
      public void call() {
        log.info("비즈니스 로직1 실행");
      }
    });
    context.execute(new Strategy() {
      @Override
      public void call() {
        log.info("비즈니스 로직2 실행");
      }
    });
  }

  @Test
  void strategyV3(){
    ContextV2 context = new ContextV2();

    context.execute(() -> log.info("비즈니스 로직1 실행"));
    //실행할 코드 조각을 넘기는 거
    context.execute(() -> log.info("비즈니스 로직2 실행"));
  }
}

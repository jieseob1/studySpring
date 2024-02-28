package com.spring.advance.v0.trace.strategy;

import com.spring.advance.v0.trace.strategy.code.strategy.ContextV1;
import com.spring.advance.v0.trace.strategy.code.strategy.Strategy;
import com.spring.advance.v0.trace.strategy.code.strategy.StrategyLogic1;
import com.spring.advance.v0.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
  //필드에 전략을 보관하는 방식
  @Test
  void strategyV0() {
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
  void strategyV1() {
    Strategy strategyLogic1 = new StrategyLogic1();
    ContextV1 context1 = new ContextV1(strategyLogic1);
    context1.execute();

    Strategy strategyLogic2 = new StrategyLogic2();
    ContextV1 context2 = new ContextV1(strategyLogic2);
    context2.execute();
  }

  @Test
  void strategyV2() {
    Strategy strategyLogic1 = new StrategyLogic1(){
      @Override
      public void call() {
        log.info("비즈니스 로직1 실행");
      }
    };
    Strategy strategyLogic2 = new StrategyLogic2(){
      @Override
      public void call() {
        log.info("비즈니스 로직2 실행");
      }
    };

    ContextV1 context1 = new ContextV1(strategyLogic1);
    context1.execute();
    log.info("strategyLogic1={}", strategyLogic1.getClass()); //$1
    ContextV1 context2 = new ContextV1(strategyLogic2);
    context2.execute();
    log.info("strategyLogic2={}", strategyLogic2.getClass()); // $2
  }

  @Test
  void strategyV3() {
    ContextV1 context1 = new ContextV1(new StrategyLogic1(){
      @Override
      public void call() {
        log.info("비즈니스 로직1 실행");
      }
    });
    context1.execute();
    ContextV1 context2 = new ContextV1(new StrategyLogic1(){
      @Override
      public void call() {
        log.info("비즈니스 로직2 실행");
      }
    });
    context2.execute();
  }
  @Test
  void strategyV4() {
    ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
    context1.execute();
    ContextV1 context2 = new ContextV1(new StrategyLogic1(){
      @Override
      public void call() {
        log.info("비즈니스 로직2 실행");
      }
    });
    context2.execute();
  }
}

package com.spring.advance.trace.template;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<T> {// 반환타입이 조금씩 다르기 때문에 generic 넣어줌

  //템플릿 메서드 패턴 => 자식에서 call을 구현하기로 하고, 공통된 부분은 AbstractTemplate에서 만듬
  private final LogTrace trace; // 로그 관련 시작, 끝, 예외

  public AbstractTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public T execute(String message) {
    TraceStatus status = null;//id,시작 시간, message
    try {
      log.info("trace 값 = {}", trace);
      status = trace.begin(message);
      log.info("status 값 ={}", status);
      //로직 호출 => 원래 orderItem 같은거
      T result = call();
      log.info("result 값 = {}", result);
      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  protected abstract T call();
}

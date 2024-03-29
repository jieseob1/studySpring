package com.spring.advance.trace.logtrace;

import com.spring.advance.trace.TraceId;
import com.spring.advance.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace{
  // 파라미터를 넘기지 않고, TraceId 동기화 가능

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<==";
  private static final String EX_PREFIX = "<X-";

  private TraceId traceIdHolder; //traceId 동기화, 동시성 이슈 발생
  // 추적 아이디 => id, level
  @Override
  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = traceIdHolder;
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()),message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId() { //없으면 만들어 주고, 있으면 다음 레벨
    if (traceIdHolder == null) {
      traceIdHolder = new TraceId();
    } else {
      traceIdHolder = traceIdHolder.createNextId(); // 다음  아이디 넣어주는 과정
    }
  }

  private void releaseTraceId() {
    if (traceIdHolder.isFirstLevel()) {
      traceIdHolder = null;
    } else {
      traceIdHolder = traceIdHolder.createPreviousId();
    }
  }

  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append( (i == level - 1) ? "|" + prefix : "|   ");
    }
    return sb.toString();
  }
  @Override
  public void end(TraceStatus status) {
    complete(status, null);
  }

  private void complete(TraceStatus status, Exception e) {
    Long stopTimeMs = System.currentTimeMillis();
    long resultTimeMs = stopTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(),addSpace(COMPLETE_PREFIX, traceId.getLevel()), resultTimeMs);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
    }
  }

  @Override
  public void exception(TraceStatus status, Exception e) {
    complete(status, e);
  }
}

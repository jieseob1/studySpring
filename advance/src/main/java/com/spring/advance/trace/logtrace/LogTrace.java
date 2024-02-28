package com.spring.advance.trace.logtrace;

import com.spring.advance.trace.TraceStatus;

public interface LogTrace { // 로그 추적
  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);
}


package com.spring.advance.trace;

public class TraceStatus { // 추적 상태 => id, time,message -> 하나의 로그에 대한 상태인 듯
    private TraceId traceId;
    private Long startTimeMs; //시작 ms 시간
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}

package com.spring.advance.trace.helloTrace;

import com.spring.advance.trace.TraceId;
import com.spring.advance.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 { // 싱클톤으로 쓰는 목적
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    public TraceStatus begin(String message) { // message넣음
        TraceId traceId = new TraceId(); //traceId 객체 생성
        Long startTimeMs = System.currentTimeMillis(); // 현재 시간
        log.info("[{}] {}{}", traceId.getId(),  addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message); // traceStatus 객체 생성 => 해당 부분 나중에 end부분으로 파라미터 전달 위함
    } // TraceStatus는 로그 하나의 대한 단위

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) { // exception 보여줄거 필요
        complete(status, e);
    } // 정상이 아닌경우

    private void complete(TraceStatus status, Exception e) { // 로그 만들어 주는 부분
        Long stopTimeMs = System.currentTimeMillis();
        Long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();// TraceId => id,level
        if (e == null) { // no error message => 정상 기동
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel())
                    , status.getMessage());
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }

    private static String addSpace(String prefix, int level) { // addSpace
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}

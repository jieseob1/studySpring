package com.spring.advance.v0.trace.helloTrace;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV1;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());

    }
}
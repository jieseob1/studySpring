package com.spring.advance.v4;


import com.spring.advance.trace.TraceId;
import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV2;
import com.spring.advance.trace.logtrace.LogTrace;
import com.spring.advance.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;


    public void save(String itemId) {
        AbstractTemplate template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                //저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("에외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

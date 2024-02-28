package com.spring.advance.v4;

import com.spring.advance.trace.TraceId;
import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV2;
import com.spring.advance.trace.logtrace.LogTrace;
import com.spring.advance.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;
    public void orderItem(String itemId) {
        AbstractTemplate template = new AbstractTemplate(trace) {
            @Override
            protected Object call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    } //Business Logic

}

package com.spring.advance.v2;

import com.spring.advance.trace.TraceId;
import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;
    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
//            status = helloTrace.begin("OrderService.orderitem");
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(),itemId); // save부분에 status.getTraceId 추가
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    } //Business Logic

}

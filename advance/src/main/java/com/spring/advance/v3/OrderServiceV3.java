package com.spring.advance.v3;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
//            status = helloTrace.begin("OrderService.orderitem");
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId); // save부분에 status.getTraceId 추가
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    } //Business Logic

}

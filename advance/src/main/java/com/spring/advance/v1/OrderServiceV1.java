package com.spring.advance.v1;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 helloTrace;
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = helloTrace.begin("OrderService.orderitem");
            orderRepository.save(itemId);
            helloTrace.end(status);
        } catch (Exception e){
            helloTrace.exception(status, e);
            throw e;
        }
        orderRepository.save(itemId);
    } //Business Logic

}

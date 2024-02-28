package com.spring.advance.v2;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    // 해당 부분에서 helloTrace를 사용하게 바뀜
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()"); // traceStatus를 return 해준다.
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
        orderService.orderItem(status.getTraceId(),itemId);
        return "ok";
    }
}

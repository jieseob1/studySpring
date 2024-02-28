package com.spring.advance.v1;

import com.spring.advance.trace.TraceStatus;
import com.spring.advance.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    // 해당 부분에서 helloTrace를 사용하게 바뀜
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 helloTrace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null; // 그냥 mesasge,id 정도 가지고 오는 용도
        try {
            traceStatus = helloTrace.begin("OrderController.request()"); // traceStatus를 return 해준다.
            orderService.orderItem(itemId);
            helloTrace.end(traceStatus);
        } catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;
        }
        orderService.orderItem(itemId);
        return "ok";
    }
}

package com.basic.springbasic.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 주문 생성하면 return 으로 Order반환

}

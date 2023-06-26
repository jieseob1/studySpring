package com.basic.springbasic.singleton;

public class StatefulService {
//    private int price; //상태를 유지하는 필드 => 공유되는 필드 => 싱글톤이므로(스프링 관련 싱글톤)

    public int order(String name, int price) {
        System.out.println("name = " + name + " price " + price);
        return price; // 지역 변수
    }

//    public int getPrice() {
//        return price;
//    }

}

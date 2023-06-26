package com.basic.springbasic.singleton;

public class SingletonService {
    private  static final SingletonService instance = new SingletonService();//자기 자신 선언

    //자기 자신 내부에 static으로 하나만 가지고 있음 => 클래스 레벨로 올라가므로 한번만
    public static SingletonService getInstance() { //자바가 초반에 실행될때, SingletonService 부분 보고 참조시켠호음
        return instance;
    } //조회할때 getInstance사용한다.

    private  SingletonService() { //생성자 부분
    }

    public void logic() {
        System.out.println(" 싱글톤 객체 로직 호출");
    }
}

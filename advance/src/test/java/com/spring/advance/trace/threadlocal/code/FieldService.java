package com.spring.advance.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FieldService {
  private String nameStore; // 이름 저장

  public String logic(String name) {
    log.info("저장 name={} -> nameStore={}", name, nameStore);
    nameStore = name;
    sleep(1000);
    log.info("조회 nameStore={}", nameStore);
    return nameStore;
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

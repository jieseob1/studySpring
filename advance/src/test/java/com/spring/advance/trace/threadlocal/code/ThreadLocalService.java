package com.spring.advance.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

  private ThreadLocal<String> nameStore = new ThreadLocal<>();

  public String logic(String name) {

  }
}

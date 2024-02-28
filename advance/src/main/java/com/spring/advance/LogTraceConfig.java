package com.spring.advance;

import com.spring.advance.trace.logtrace.FieldLogTrace;
import com.spring.advance.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new FieldLogTrace();
  }

}

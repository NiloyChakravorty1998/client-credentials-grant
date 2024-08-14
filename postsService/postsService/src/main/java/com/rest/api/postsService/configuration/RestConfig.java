package com.rest.api.postsService.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@Slf4j
public class RestConfig {

  @Bean
  public RestTemplate getTemplate() {
     log.info("Initializing Rest Template : ");
      return new RestTemplate();
  }
}

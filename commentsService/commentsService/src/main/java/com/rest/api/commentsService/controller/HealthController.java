package com.rest.api.commentsService.controller;

import com.rest.api.commentsService.dto.ResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@Slf4j
@RequiredArgsConstructor
public class HealthController {
    private final Environment env;

    @GetMapping("")
    public ResponseEntity<?> healthCheck(){
        Map<String, String> response = new HashMap<>();
        response.put("status", "Up and running !");
        response.put("currentTime", new Date(System.currentTimeMillis()).toString());
        response.put("appName", env.getProperty("spring.application.name"));
        response.put("downstream", env.getProperty("backend.uri"));
        ResponseVO ovo = ResponseVO.builder().response(response).exception(null).build();
        return new ResponseEntity<>(ovo, HttpStatus.OK);
    }
}

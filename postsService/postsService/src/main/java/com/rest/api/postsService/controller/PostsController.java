package com.rest.api.postsService.controller;


import com.rest.api.postsService.dto.RequestVO;
import com.rest.api.postsService.dto.ResponseVO;
import com.rest.api.postsService.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
@Slf4j
public class PostsController {
    private final PostsService postsService;

    @GetMapping(path = "")
    public ResponseEntity<?> getPosts(@RequestBody RequestVO ivo){
        log.info("getPosts() invoked : ");
        ResponseVO ovo = postsService.getPosts(ivo);
        return new ResponseEntity<>(ovo, HttpStatus.OK);
    }



}

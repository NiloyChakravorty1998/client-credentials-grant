package com.rest.api.commentsService.controller;

import com.rest.api.commentsService.dto.RequestVO;
import com.rest.api.commentsService.dto.ResponseVO;
import com.rest.api.commentsService.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("")
    public ResponseEntity<?> getAllComments(@RequestBody RequestVO ivo){
        log.info("getComments() invoked");
        ResponseVO ovo = commentsService.getComments(ivo);
         return new ResponseEntity<>(ovo, HttpStatus.OK);
    }
}

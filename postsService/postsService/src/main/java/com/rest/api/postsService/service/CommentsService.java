package com.rest.api.postsService.service;

import com.rest.api.postsService.dto.CommentRequestVO;
import com.rest.api.postsService.dto.CommentVO;
import com.rest.api.postsService.dto.CommentResponseVO;
import com.rest.api.postsService.dto.OauthAuthServerResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService {
    private final OAuthService oAuthService;
    private final RestTemplate restTemplate;

    @Value("${comment-service.uri}")
    private String COMMENT_SERVICE_URI;

    public List<CommentVO> getCommentsForPost(String postId) {
        // TODO - fetch comments for postId
        log.info("fetch comments for postId : " +postId);
        CommentRequestVO ivo = CommentRequestVO.builder().postId(postId).build();
        CommentResponseVO responseVO = doCommentsServiceAPICall(ivo).orElseThrow(
                () -> new RuntimeException("Could not fetch comments for post Id : " +postId)
        );
        return  responseVO.getResponse();
    }

    public Optional<CommentResponseVO> doCommentsServiceAPICall(CommentRequestVO ivo){
        HttpHeaders headers = new HttpHeaders();
        OauthAuthServerResponseVO authResponse =
                oAuthService.getAccessToken()
                        .orElseThrow(() ->  new RuntimeException("Token was not generated "));
        //GET ACCESS TOKEN FROM AUTH SERVER
        String bearerToken = authResponse.getAccessToken();
        headers.setBearerAuth(bearerToken);
        headers.add("Content-Type", "application/json");
        HttpEntity<CommentRequestVO> entity =new HttpEntity<>(ivo, headers);
        //API CALL TO RESOURCE SERVER / COMMENT MICROSERVICE
        try {
            ResponseEntity<CommentResponseVO> response = restTemplate.exchange(
                    COMMENT_SERVICE_URI, HttpMethod.POST,entity, CommentResponseVO.class
            );
            return Optional.ofNullable(response.getBody());
        }catch (Exception e){
            log.error("Error while fetching comments ");
            throw new RuntimeException("Error while fetching comments ");
        }
    }

}



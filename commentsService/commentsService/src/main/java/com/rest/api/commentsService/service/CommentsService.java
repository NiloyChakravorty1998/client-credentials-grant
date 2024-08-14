package com.rest.api.commentsService.service;

import com.rest.api.commentsService.dto.CommentVO;
import com.rest.api.commentsService.dto.RequestVO;
import com.rest.api.commentsService.dto.ResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService {
    private final Environment environment;
    private final RestTemplate restTemplate;

    public ResponseVO getComments(RequestVO requestVO) {
        String postId = requestVO.getPostId() ;
        String id = requestVO.getId() ;
        String baseUri = environment.getProperty("backend.uri");
        if (baseUri == null) {
            log.error("Backend URI is not configured.");
            throw new RuntimeException("Backend URI is not configured.");
        }
        String url = baseUri;
        try {
            log.info("Fetching comments from URL: " + url);
            ResponseEntity<CommentVO[]> responseEntity = restTemplate.getForEntity(url, CommentVO[].class);
            CommentVO[] commentsArray = responseEntity.getBody();

            if (null!=commentsArray && (postId !=null || id !=null)) {
                List<CommentVO> result = List.of(commentsArray)
                        .stream().filter(e -> e.getId().equals(id) || e.getPostId().equals(postId))
                        .collect(Collectors.toList());
                // Assuming ResponseVO has a constructor or method to set comments
                ResponseVO ovo = new ResponseVO();
                ovo.setResponse(result);
                return ovo;
            }
            if(null != commentsArray && (postId ==null && id ==null)) {
                List<CommentVO> result = List.of(commentsArray);
                ResponseVO ovo = new ResponseVO();
                ovo.setResponse(result);
                return ovo;
            } else {
                log.warn("No comments found for the given request.");
                // Handle empty response case if needed
                return new ResponseVO();
            }
        } catch (Exception e) {
            log.error("Error fetching comments", e);
            throw new RuntimeException("Error fetching comments", e);
        }
    }
}

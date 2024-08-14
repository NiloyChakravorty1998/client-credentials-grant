package com.rest.api.postsService.service;

import com.rest.api.postsService.dto.PostVO;
import com.rest.api.postsService.dto.RequestVO;
import com.rest.api.postsService.dto.ResponseVO;
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
public class PostsService {
    private final Environment environment;
    private final RestTemplate restTemplate;

    public ResponseVO getPosts(RequestVO requestVO) {
        String userId = requestVO.getUserId() ;
        String id = requestVO.getId() ;
        String baseUri = environment.getProperty("backend.uri");
        if (baseUri == null) {
            log.error("Backend URI is not configured.");
            throw new RuntimeException("Backend URI is not configured.");
        }
        String url = baseUri;
        try {
            log.info("Fetching posts from URL: " + url);
            ResponseEntity<PostVO[]> responseEntity = restTemplate.getForEntity(url, PostVO[].class);
            PostVO[] commentsArray = responseEntity.getBody();

            if (null!=commentsArray && (userId !=null || id !=null)) {
                List<PostVO> result = List.of(commentsArray)
                        .stream().filter(e -> e.getId().equals(id) || e.getUserId().equals(userId))
                        .collect(Collectors.toList());
                // Assuming ResponseVO has a constructor or method to set comments
                return ResponseVO.builder().response(result).exception(null).build();
            }
            if(null != commentsArray && (userId ==null && id ==null)) {
                List<PostVO> result = List.of(commentsArray);
                return ResponseVO.builder().response(result).exception(null).build();
            } else {
                log.warn("No posts found for the given request.");
                // Handle empty response case if needed
                return ResponseVO.builder().exception(new RuntimeException("No posts found for the given request")).build();
            }
        } catch (Exception e) {
            log.error("Error fetching posts", e);
            throw new RuntimeException("Error fetching posts", e);
        }
    }
}

package com.rest.api.postsService.service;

import com.rest.api.postsService.configuration.OAuthClientConfig;
import com.rest.api.postsService.dto.OauthAuthServerResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {
    private final RestTemplate restTemplate;
    private final OAuthClientConfig oAuthClientConfig;

    @Value("${oauth.auth.uri}")
    private  String OAUTH2_SERVER_URI;

    public Optional<OauthAuthServerResponseVO> getAccessToken() {
        log.info("Invoking getAccessToken() ");
        OauthAuthServerResponseVO responseVO;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> reqBody =
                new LinkedMultiValueMap<>();
        reqBody.add("grant_type",oAuthClientConfig.getGrantType());
        reqBody.add("client_id",oAuthClientConfig.getClientId());
        reqBody.add("client_secret",oAuthClientConfig.getClientSecret());
        reqBody.add("scope",oAuthClientConfig.getScope());
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(reqBody, headers);
        try {
            ResponseEntity<OauthAuthServerResponseVO> response= restTemplate.exchange(
                    OAUTH2_SERVER_URI, HttpMethod.POST,
                    request,OauthAuthServerResponseVO.class);
            responseVO = response.getBody();
            return Optional.of(responseVO);
        }catch (Exception e){
            log.error("Error while generating access token : " +e);
            throw new RuntimeException("Error while generating access token : " +e);
        }
    }
}

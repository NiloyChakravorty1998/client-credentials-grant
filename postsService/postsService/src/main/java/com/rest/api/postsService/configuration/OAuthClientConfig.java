package com.rest.api.postsService.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "oauth")
@Data
@NoArgsConstructor
public class OAuthClientConfig {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String scope;
}

package com.branchapp.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GithubClientConfig {

    @Bean
    WebClient githubWebClient(@Value("${github.api.url}") String githubApiUrl) {
        return  WebClient.builder()
                .baseUrl(githubApiUrl).build();
    }
}

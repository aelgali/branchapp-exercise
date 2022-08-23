package com.branchapp.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GithubClientConfig {

    @Bean
    WebClient githubWebClient() {
        return  WebClient.builder()
                .baseUrl("https://api.github.com").build();
    }
}

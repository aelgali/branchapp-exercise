package com.branchapp.integration.service.github;

import com.branchapp.integration.dto.RepoDetailsDTO;
import com.branchapp.integration.dto.UserDetailsDTO;
import com.branchapp.integration.service.IntegrationService;
import com.branchapp.integration.service.github.response.GithubRepoResponse;
import com.branchapp.integration.service.github.response.GithubUserResponse;
import com.branchapp.integration.exception.ResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

@Service
public class GithubIntegrationService implements IntegrationService {
    private final WebClient githubWebClient;

    public GithubIntegrationService(WebClient githubWebClient) {
        this.githubWebClient = githubWebClient;
    }

    @Override
    public Mono<UserDetailsDTO> listUserRepositories(String username) {

        Mono<GithubUserResponse> userProfile = getUserProfile(username);
        Mono<List<GithubRepoResponse>> repos = getUserRepos(username);

        return userProfile.zipWhen(githubUserResponse -> repos, this::mapToUserDetailsDTO);
    }

    private Mono<GithubUserResponse> getUserProfile(String username) {
        return githubWebClient
                .get().uri(uriBuilder -> uriBuilder.path("/users/{username}").build(username))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        //todo map more statuses to custom exception if needed
                        switch (clientResponse.statusCode()) {
                            case NOT_FOUND ->
                                    Mono.error(new ResourceNotFoundException(ResourceNotFoundException.Resource.USER, username));
                            default -> Mono.error(new RuntimeException("Unknown error"));
                        })
                .bodyToMono(GithubUserResponse.class);
    }

    private Mono<List<GithubRepoResponse>> getUserRepos(String username) {
        return githubWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    private UserDetailsDTO mapToUserDetailsDTO(GithubUserResponse userResponse, List<GithubRepoResponse> reposResponse) {
        return new UserDetailsDTO(userResponse.login(), userResponse.name(), userResponse.avatarUrl(),
                userResponse.location(), userResponse.email(),
                userResponse.htmlUrl(), userResponse.createAt(),
                reposResponse
                        .stream()
                        .map(repoResponse -> new RepoDetailsDTO(repoResponse.name(), repoResponse.htmlUrl()))
                        .collect(Collectors.toList()));
    }
}

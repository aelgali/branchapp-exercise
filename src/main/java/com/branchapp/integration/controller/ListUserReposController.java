package com.branchapp.integration.controller;

import com.branchapp.integration.dto.UserDetailsDTO;
import com.branchapp.integration.service.IntegrationService;
import com.google.common.base.Preconditions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@RestController
public class ListUserReposController {

    private final IntegrationService githubIntegrationService;

    public ListUserReposController(IntegrationService githubIntegrationService) {
        this.githubIntegrationService = githubIntegrationService;
    }

    @GetMapping("/users/{username}/repos")
    public @ResponseBody Mono<UserDetailsDTO> listRepos(@PathVariable("username") @NotBlank String username) {

        Preconditions.checkArgument(Objects.nonNull(username) && !username.isBlank(), "username is required.");

        return githubIntegrationService.listUserRepositories(username);
    }
}

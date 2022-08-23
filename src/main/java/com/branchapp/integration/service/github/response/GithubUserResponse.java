package com.branchapp.integration.service.github.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GithubUserResponse(String login, String avatarUrl, String location, String email, String htmlUrl,
                                 String name, LocalDateTime createAt) {

    @JsonCreator
    public GithubUserResponse(@JsonProperty("login") String login,
                              @JsonProperty("avatar_url") String avatarUrl,
                              @JsonProperty("location") String location,
                              @JsonProperty("email") String email,
                              @JsonProperty("html_url") String htmlUrl,
                              @JsonProperty("name") String name,
                              @JsonProperty("created_at") LocalDateTime createAt) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.location = location;
        this.email = email;
        this.htmlUrl = htmlUrl;
        this.name = name;
        this.createAt = createAt;
    }
}

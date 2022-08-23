package com.branchapp.integration.service.github.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubRepoResponse(String name, String htmlUrl) {
    public GithubRepoResponse(@JsonProperty("name") String name, @JsonProperty("html_url") String htmlUrl) {
        this.name = name;
        this.htmlUrl = htmlUrl;
    }
}

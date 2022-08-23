package com.branchapp.integration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record UserDetailsDTO(@JsonProperty("user_name") String username,
                             @JsonProperty("display_name") String displayName,
                             @JsonProperty("avatar") String avatar,
                             @JsonProperty("geo_location") String geoLocation,
                             @JsonProperty("email") String email,
                             @JsonProperty("url") String url,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                             @JsonProperty("created_date") LocalDateTime createdDate,
                             List<RepoDetailsDTO> repos) {
}

package com.branchapp.integration.service;

import com.branchapp.integration.dto.UserDetailsDTO;
import reactor.core.publisher.Mono;


public interface IntegrationService {

    Mono<UserDetailsDTO> listUserRepositories(String username);
}

package com.branchapp.integration.controller

import com.branchapp.integration.dto.RepoDetailsDTO
import com.branchapp.integration.dto.UserDetailsDTO
import com.branchapp.integration.service.IntegrationService
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.that

class ListUserReposControllerTest extends Specification {


    def "List user's repositories"() {
        given: "a username"
        def username = 'octocat'

        and: 'a list user repos controller'
        def integrationServiceMock = Mock(IntegrationService.class) {
            1 * listUserRepositories(username) >> Mono.just(new UserDetailsDTO(
                    'username', 'user display name', 'http://avatar.com',
                    'The Location', 'user@email.com', 'http://happy.coding.com',
                    LocalDateTime.now(),
                    [new RepoDetailsDTO("repo1", "http://repo1.com")]

            ))
        }
        def listUserReposController = new ListUserReposController(integrationServiceMock)

        when: 'the list user repositories endpoint is called'
        def userRepositoriesResponse = listUserReposController.listRepos(username)

        then: 'a valid response should be returned'
        def userDetails = userRepositoriesResponse.block()
        that userDetails, is(not(nullValue()))
        that userDetails.username(), is(equalTo('username'))
    }

    @Unroll
    def 'Should validate username is not blank or null' () {
        given: 'an invalid username'

        and: 'a list user repos controller'
        def listUserReposController = new ListUserReposController(Mock(IntegrationService.class))

        when: 'the list user repositories endpoint is called with an invalid username'
        def userRepositoriesResponse = listUserReposController.listRepos(invalidUsername)

        then: 'an invalid argument exception should be thrown'
        IllegalArgumentException ex = thrown()

        and: 'a message indicating that the arugment name'
        that ex.message, is(equalTo('username is required.'))

        where:
        invalidUsername << [null, '', '   ']

    }
}

# BranchApp integration client
This is a sample application to demonstrate a client integration with github.

The application was implemented using [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html).
and cotainerized using [Docker](https://www.docker.com/).

## How to run
To run the application locally you will need to [install docker](https://docs.docker.com/get-docker/) on your local machine.

### Build
Run the following command from the root directory of the repository, to build the docker image.
```shell
docker build -t branchapp .
```

### Run
Run the following command to start the app
```shell
docker run --rm -it -p "8090:8080" branchapp
```

The application should be running on port `8090`
http://localhost:8090/users/octocat/repos

## Future enhancements
- Provide caching mechanism
Introducing caching service to reduce the number of API calls to github.
A good option would be a [Redis](https://redis.com/) based caching service.
- Add pagination support, since github api `/users/<USERNAME>/repos` endpoint 
defaults to 30 repos per request and a maximum of 100. 
- Additional test coverage to include (integration, specification) tests.

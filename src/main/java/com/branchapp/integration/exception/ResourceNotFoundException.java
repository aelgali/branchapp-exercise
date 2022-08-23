package com.branchapp.integration.exception;

public class ResourceNotFoundException extends ApplicationException {

    public enum Resource {
        USER
    }
    private final Resource resource;

    public ResourceNotFoundException(Resource resource, String resourceValue) {
        super(ExceptionCode.RESOURCE_NOT_FOUND, String.format("Resource %s %s, not found.", resource, resourceValue));
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}

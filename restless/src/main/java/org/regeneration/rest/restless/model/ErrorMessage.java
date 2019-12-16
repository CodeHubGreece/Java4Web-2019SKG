package org.regeneration.rest.restless.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMessage {

    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long resourceId;

    private int status;

    public ErrorMessage(String error, Long resourceId, int status) {
        this.error = error;
        this.resourceId = resourceId;
        this.status = status;
    }

    public ErrorMessage(String error, int status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}

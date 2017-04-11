package com.taavo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class ApiBehavior {
    private final Request request;
    private final Response response;

    @JsonCreator
    public ApiBehavior(@JsonProperty("request") Request request, @JsonProperty("response)") Response response) {
        this.request = request;
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }
}

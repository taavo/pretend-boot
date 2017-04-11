package com.taavo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Response {
    private final int status;
    private final String responseText;

    @JsonCreator
    public Response(@JsonProperty("status") int status, @JsonProperty("responseText") String responseText) {
        this.status = status;
        this.responseText = responseText;
    }

    public int getStatus() {
        return status;
    }

    public String getResponseText() {
        return responseText;
    }
}

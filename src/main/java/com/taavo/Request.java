package com.taavo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Request {
    private String method;
    private String url;

    @JsonCreator
    public Request(@JsonProperty("method") String method, @JsonProperty("url") String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}

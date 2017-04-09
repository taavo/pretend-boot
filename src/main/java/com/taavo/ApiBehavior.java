package com.taavo;

class ApiBehavior {
    private final Request request;
    private final Response response;

    public ApiBehavior(Request request, Response response) {
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

package com.taavo;

class Response {
    private final int status;
    private final String responseText;

    public Response(int status, String responseText) {
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

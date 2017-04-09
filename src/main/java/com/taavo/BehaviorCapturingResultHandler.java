package com.taavo;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

class BehaviorCapturingResultHandler implements ResultHandler {

    private ApiBehavior apiBehavior;

    public BehaviorCapturingResultHandler() {
    }

    @Override
    public void handle(MvcResult mvcResult) throws Exception {
        MockHttpServletRequest servletRequest = mvcResult.getRequest();
        Request request = new Request(servletRequest.getMethod(), servletRequest.getRequestURI());

        MockHttpServletResponse servletResponse = mvcResult.getResponse();
        Response response = new Response(servletResponse.getStatus(), servletResponse.getContentAsString());

        apiBehavior = new ApiBehavior(request, response);
    }

    public ApiBehavior getApiBehavior() {
        return apiBehavior;
    }
}

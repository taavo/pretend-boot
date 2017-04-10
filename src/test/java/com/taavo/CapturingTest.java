package com.taavo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CapturingTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void capturesMethodAndUri() throws Exception {
        BehaviorCapturingResultHandler behaviorCapturingResultHandler = new BehaviorCapturingResultHandler();

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/"))
                    .andDo(behaviorCapturingResultHandler)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Hello World")));

        ApiBehavior apiBehavior = behaviorCapturingResultHandler.getApiBehavior();

        Request requestBehavior = apiBehavior.getRequest();
        assertThat(requestBehavior.getMethod()).isEqualTo("GET");
        assertThat(requestBehavior.getUrl()).isEqualTo("/");

        Response responseBehavior = apiBehavior.getResponse();
        assertThat(responseBehavior.getStatus()).isEqualTo(200);
        assertThat(responseBehavior.getResponseText()).isEqualTo("Hello World");
    }
}

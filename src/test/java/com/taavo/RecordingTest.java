package com.taavo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecordingTest {
    public static final String DESTINATION_PATH = "js/spec/behavior/simple.json";

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void deletePreviousRecording() throws IOException {
        Files.deleteIfExists(Paths.get(DESTINATION_PATH));
    }

    @Test
    public void recordsMethodAndUri() throws Exception {
        BehaviorRecordingResultHandler behaviorRecordingResultHandler = new BehaviorRecordingResultHandler(DESTINATION_PATH);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/"))
                    .andDo(behaviorRecordingResultHandler)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Hello World")));

        String output = new String(Files.readAllBytes(Paths.get(DESTINATION_PATH)));
        ApiBehavior apiBehavior = objectMapper.readValue(output, ApiBehavior.class);

        Request requestBehavior = apiBehavior.getRequest();
        assertThat(requestBehavior.getMethod()).isEqualTo("GET");
        assertThat(requestBehavior.getUrl()).isEqualTo("/");

        Response responseBehavior = apiBehavior.getResponse();
        assertThat(responseBehavior.getStatus()).isEqualTo(200);
        assertThat(responseBehavior.getResponseText()).isEqualTo("Hello World");
    }
}

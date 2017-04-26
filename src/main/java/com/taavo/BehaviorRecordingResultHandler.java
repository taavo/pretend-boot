package com.taavo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class BehaviorRecordingResultHandler extends BehaviorCapturingResultHandler {
    private String path;
    private ObjectMapper objectMapper = new ObjectMapper();

    public BehaviorRecordingResultHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(MvcResult result) throws Exception {
        super.handle(result);

        Files.write(
                Paths.get(this.path),
                Arrays.asList(
                        objectMapper.writeValueAsString(this.getApiBehavior())
                )
        );
    }

    public static ResultHandler saveFixture(String destinationPath) {
        return new BehaviorRecordingResultHandler(destinationPath);
    }

}

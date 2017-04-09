package com.taavo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SomeController {
    @GetMapping("/")
    public @ResponseBody
    String get() {
        return "Hello World";
    }
}

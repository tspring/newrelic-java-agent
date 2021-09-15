package com.nr.agent.instrumentation.stub;

import org.springframework.web.bind.annotation.GetMapping;

public class BaseControllerStub {
    @GetMapping("/parent")
    public String parent() {
        return "parent";
    }
}

package com.nr.agent.instrumentation.testCases;

import org.springframework.web.bind.annotation.GetMapping;

public class Parent {
    @GetMapping("/parent")
    public String inheritedPath() {
        return "parent";
    }
}

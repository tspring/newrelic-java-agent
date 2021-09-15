package com.nr.agent.instrumentation.testCases;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/child")
public class Child extends Parent {

    @GetMapping("/notInherited")
    public String notInheritedPath() {
        return "childNotInherited";
    }
}

package com.nr.agent.instrumentation.stub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/child")
public class ChildController {

    @GetMapping("/notInherited")
    public String notInherited() {
        return "childNotInherited";
    }
}

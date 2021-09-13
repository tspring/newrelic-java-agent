package com.nr.agent.instrumentation.stub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class BaseControllerStub {
    @GetMapping
    public ResponseEntity<String> somethingUnique() {
        return new ResponseEntity<>(BaseControllerStub.class.getCanonicalName(), HttpStatus.OK);
    }
}

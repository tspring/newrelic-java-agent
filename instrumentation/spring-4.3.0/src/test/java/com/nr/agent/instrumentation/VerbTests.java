/*
 *
 *  * Copyright 2020 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.nr.agent.instrumentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/verb")
public class VerbTests {
    @GetMapping(path = "/Get")
    public String getMapping() {
        return "getmapping";
    }

    @PatchMapping(path = "/Patch")
    public String patchMapping() {
        return "patchmapping";
    }

    @PostMapping(path = "/Post")
    public String postMapping() {
        return "postmapping";
    }

    @PutMapping(path = "/Put")
    public String putMapping() {
        return "putmapping";
    }

    @DeleteMapping(path = "/Delete")
    public String deleteMapping() {
        return "deletemapping";
    }
}

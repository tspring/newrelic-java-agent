/*
 *
 *  * Copyright 2020 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.nr.agent.instrumentation;

import com.nr.agent.instrumentation.testCases.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPathAnnotationForMethod {
	
	@RequestMapping(path = "/pathTest")
	public String testPathAnnotation() {
	    return "methodPath";
	}
}

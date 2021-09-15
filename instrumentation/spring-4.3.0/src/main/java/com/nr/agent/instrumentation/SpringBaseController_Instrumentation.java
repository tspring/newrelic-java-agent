/*
 *
 *  * Copyright 2020 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.nr.agent.instrumentation;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.agent.bridge.Transaction;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.newrelic.api.agent.weaver.Weaver;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

import static com.nr.agent.instrumentation.SpringControllerUtility.log;
import static com.nr.agent.instrumentation.SpringControllerUtility.processAnnotations;

public class SpringBaseController_Instrumentation {

    @WeaveWithAnnotation(annotationClasses = {
            "org.springframework.web.bind.annotation.RequestMapping",
            "org.springframework.web.bind.annotation.PatchMapping",
            "org.springframework.web.bind.annotation.PutMapping",
            "org.springframework.web.bind.annotation.GetMapping",
            "org.springframework.web.bind.annotation.PostMapping",
            "org.springframework.web.bind.annotation.DeleteMapping" })
    @WeaveIntoAllMethods
    @Trace
    private static void requestMapping() {
        SpringControllerUtility.log("in BASE requestMapping()");
        Transaction transaction = AgentBridge.getAgent().getTransaction(false);
        log("transaction=" + transaction);
        if (transaction != null) {
            RequestMapping rootPathMapping = Weaver.getClassAnnotation(RequestMapping.class);
            String rootPath = null;
            log("rootPathMapping=" + rootPathMapping);
            if (rootPathMapping != null) {
                log("rootPathMapping=" + String.join(",", rootPathMapping.value()));
                rootPath = SpringControllerUtility.getPathValue(rootPathMapping.value(), rootPathMapping.path());
                log("rootPath=" + rootPath);
            }

            // the ordering of the following is important. RequestMapping overrides the new annotations. Then it goes:
            // PUT, DELETE, POST, GET
            if (Weaver.getMethodAnnotation(RequestMapping.class) != null) {
                RequestMapping methodPathMapping = Weaver.getMethodAnnotation(RequestMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, methodPathMapping.method(), rootPath, methodPath, MethodHandles.lookup().lookupClass());
            } else if (Weaver.getMethodAnnotation(PutMapping.class) != null) {
                PutMapping methodPathMapping = Weaver.getMethodAnnotation(PutMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, new RequestMethod[] { RequestMethod.PUT }, rootPath, methodPath, MethodHandles.lookup().lookupClass());
            } else if (Weaver.getMethodAnnotation(DeleteMapping.class) != null) {
                DeleteMapping methodPathMapping = Weaver.getMethodAnnotation(DeleteMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, new RequestMethod[] { RequestMethod.DELETE }, rootPath, methodPath, MethodHandles.lookup().lookupClass());
            } else if (Weaver.getMethodAnnotation(PostMapping.class) != null) {
                PostMapping methodPathMapping = Weaver.getMethodAnnotation(PostMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, new RequestMethod[] { RequestMethod.POST }, rootPath, methodPath, MethodHandles.lookup().lookupClass());
            } else if (Weaver.getMethodAnnotation(PatchMapping.class) != null) {
                PatchMapping methodPathMapping = Weaver.getMethodAnnotation(PatchMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, new RequestMethod[] { RequestMethod.PATCH }, rootPath, methodPath, MethodHandles.lookup().lookupClass());
            } else if (Weaver.getMethodAnnotation(GetMapping.class) != null) {
                GetMapping methodPathMapping = Weaver.getMethodAnnotation(GetMapping.class);
                String methodPath = SpringControllerUtility.getPathValue(methodPathMapping.value(),
                        methodPathMapping.path());
                processAnnotations(transaction, new RequestMethod[] { RequestMethod.GET }, rootPath, methodPath, MethodHandles.lookup().lookupClass());
            }
            else {
                log("*** NO ANNOTATION FOUND ***");
            }
        }
    }

}

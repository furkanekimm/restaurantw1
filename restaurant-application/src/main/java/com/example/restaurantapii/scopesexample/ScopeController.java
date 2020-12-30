package com.example.restaurantapii.scopesexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@Controller
@RequestMapping("/scope")
public class ScopeController implements Serializable {
    @Autowired
    private PrototypeScopeComponent prototypeScopeComponent;

    @Autowired
    private SessionScopeComponent sessionScopeComponent;

    @Autowired
    private SingletonScopeComponent singletonScopeComponent;

    @Autowired
    private RequestScopeComponent requestScopeComponent;

    @GetMapping("/session")
    public long getSessionScopeData(){
        sessionScopeComponent.printInstanceScopeInfo();
        return System.identityHashCode(sessionScopeComponent);
    }

    @GetMapping("/singleton")
    public long getSingletonScopeData(){
        singletonScopeComponent.printInstanceInfo();
        return System.identityHashCode(singletonScopeComponent);
    }

    @GetMapping("/request")
    public long getRequestScopeData(){
        requestScopeComponent.printInstanceInfo();
        return System.identityHashCode(requestScopeComponent);
    }

    @GetMapping("/prototype")
    public long getPrototypeScopeData(){
        prototypeScopeComponent.printInstanceInfo();
        return System.identityHashCode(prototypeScopeComponent);
    }
}

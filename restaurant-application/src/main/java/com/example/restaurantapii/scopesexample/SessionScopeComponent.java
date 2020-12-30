package com.example.restaurantapii.scopesexample;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class SessionScopeComponent implements Serializable {

    private static int instanceCount;

    public SessionScopeComponent(){
        instanceCount++;
    }

    public void printInstanceScopeInfo(){
        System.out.println("SessionScope instanceCount: " + instanceCount + "-hashCode : " +this.hashCode());
    }
}

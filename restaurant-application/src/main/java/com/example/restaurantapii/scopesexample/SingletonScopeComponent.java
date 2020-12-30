package com.example.restaurantapii.scopesexample;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SingletonScopeComponent implements Serializable {

    private static int instanceCount;

    public SingletonScopeComponent(){
        instanceCount++;
    }

    public void printInstanceInfo(){
        System.out.println("SingletonScope instanceCount: " + instanceCount + "-hashCode: " +this.hashCode());
    }
}

package com.example.restaurantapii.exceptions;

public class BusinessRuleException extends RuntimeException {
    private Integer errorCode;

    public BusinessRuleException(Errors e) {
        super(e.toString());
        this.errorCode=e.getValue();
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }
}

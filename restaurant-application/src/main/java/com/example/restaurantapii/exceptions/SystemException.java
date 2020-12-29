package com.example.restaurantapii.exceptions;

public class SystemException extends RuntimeException {
    private Integer errorCode;
    public SystemException(Errors e) {
        super(e.toString());
        this.errorCode=e.getValue();
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }
}

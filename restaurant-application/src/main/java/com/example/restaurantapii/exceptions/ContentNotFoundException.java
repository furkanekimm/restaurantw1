package com.example.restaurantapii.exceptions;

public class ContentNotFoundException extends RuntimeException {
    private Integer errorCode;

    public ContentNotFoundException(Errors e) {
        super(e.toString());
        this.errorCode=e.getValue();
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }

}

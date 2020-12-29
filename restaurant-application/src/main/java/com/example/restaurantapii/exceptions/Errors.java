package com.example.restaurantapii.exceptions;



public enum Errors {
    RECORD_SHOULD_GET_NAME("RECORD_SHOULD_GET_NAME",1),
    ID_NOT_FOUND("ID_NOT_FOUND",2),
    RECORD_NOT_FOUND("RECORD_NOT_FOUND",3),
    ID_NULL("ID_NULL",4),
    MEDIA_NOT_FOUND("MEDIA_NOT_FOUND",5),
    LIST_IS_EMPTY("LIST_IS_EMPTY",6);



    private String messages;
    private Integer value;

    Errors(String message,Integer value){
        this.messages=message;
        this.value=value;
    }

    @Override
    public String toString() {
        return messages;
    }

    public Integer getValue(){
        return this.value;
    }

}

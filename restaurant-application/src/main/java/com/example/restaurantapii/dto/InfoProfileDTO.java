package com.example.restaurantapii.dto;


import java.io.Serializable;

public class InfoProfileDTO implements Serializable {
    private  String key;
    private  String value;

    public static final String SERVERPORT= "server.port";

    public static final String H2ENABLED= "spring.h2.console.enabled";

    public static final String DATASOURCE= "spring.datasource.url";

    public static final String SHOWSQL = "spring.jpa.show-sql";

    public static final  String HIBERNATEFORMATSQL = "spring.jpa.properties.hibernate.format_sql";

    public static final  String LOGGING = "logging.level.org.hibernate.type";

    public static final  String ACTIVEPROF = "spring.profiles.active";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

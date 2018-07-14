package com.qless.challenge.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum SourceType {
    LOCATION,
    MERCHANT;

    private static Map<String, SourceType> enumMap = new HashMap<String, SourceType>();
    static {
        enumMap.put("location", LOCATION);
        enumMap.put("merchant", MERCHANT);
    }

    @JsonCreator
    public static SourceType forValue(String value){
        return enumMap.get(value);
    }

    @JsonValue
    public String toValue(){
        return this.toString().toLowerCase();
    }

}

package com.qless.challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfo {
    private String timeZone;
    @JsonProperty("gps")
    private Coordinate coordinate;

    public ContactInfo() {
    }

    public ContactInfo(String timeZone, Coordinate coordinate) {
        this.timeZone = timeZone;
        this.coordinate = coordinate;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}

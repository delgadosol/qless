package com.qless.challenge.models;

public class Location {

    public Location() {
    }

    private Location(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static class LocationBuilder {
        private String name;

        public LocationBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Location createLocation() {
            return new Location(name);
        }
    }



}

package com.qless.challenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private String name;
    @JsonProperty("merchantInfo")
    private Merchant merchant;

    public Location() {
    }

    public Location(String name, Merchant merchant) {
        this.name = name;
        this.merchant = merchant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public static class LocationBuilder {
        private String name;
        private Merchant merchantInfo;

        public LocationBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public LocationBuilder withMerchant(Merchant merchantInfo) {
            this.merchantInfo = merchantInfo;
            return this;
        }
        public Location createLocation() {
            return new Location(name, merchantInfo);
        }
    }



}

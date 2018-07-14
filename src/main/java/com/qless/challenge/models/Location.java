package com.qless.challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String name;
    private String description;
    @JsonProperty("merchantInfo")
    private Merchant merchant;
    private NetworkSource source;
    private ContactInfo contactInfo;

    public Location() {
    }

    public Location(String name, String description, Merchant merchant, NetworkSource source, ContactInfo contactInfo) {
        this.name = name;
        this.description = description;
        this.merchant = merchant;
        this.source = source;
        this.contactInfo = contactInfo;
    }

    private Location(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setMerchant(builder.merchant);
        setSource(builder.source);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NetworkSource getSource() {
        return source;
    }

    public void setSource(NetworkSource source) {
        this.source = source;
    }


    public static final class Builder {
        private String name;
        private String description;
        private Merchant merchant;
        private NetworkSource source;
        private ContactInfo contactInfo;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withMerchant(Merchant val) {
            merchant = val;
            return this;
        }

        public Builder withSource(NetworkSource val) {
            source = val;
            return this;
        }

        public Builder withContactInfo(ContactInfo val) {
            contactInfo = val;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}

package com.qless.challenge.models;

public class Merchant {
    private String name;
    private NetworkSource source;

    public Merchant() {
    }

    public Merchant(String name, NetworkSource source) {
        this.name = name;
        this.source = source;
    }

    private Merchant(Builder builder) {
        setName(builder.name);
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

    public NetworkSource getSource() {
        return source;
    }

    public void setSource(NetworkSource source) {
        this.source = source;
    }


    public static final class Builder {
        private String name;
        private NetworkSource source;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withSource(NetworkSource val) {
            source = val;
            return this;
        }

        public Merchant build() {
            return new Merchant(this);
        }
    }
}

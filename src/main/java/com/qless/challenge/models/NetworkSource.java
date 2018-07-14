package com.qless.challenge.models;

public class NetworkSource {

    private SourceType type;
    private String globalId;
    private String hostName;
    private int hostId;
    private String indirectId;

    public NetworkSource() {
    }

    public NetworkSource(SourceType type, String globalId, String hostName, int hostId, String indirectId) {
        this.type = type;
        this.globalId = globalId;
        this.hostName = hostName;
        this.hostId = hostId;
        this.indirectId = indirectId;
    }

    private NetworkSource(Builder builder) {
        setType(builder.type);
        setGlobalId(builder.globalId);
        setHostName(builder.hostName);
        setHostId(builder.hostId);
        setIndirectId(builder.indirectId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public SourceType getType() {
        return type;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getIndirectId() {
        return indirectId;
    }

    public void setIndirectId(String indirectId) {
        this.indirectId = indirectId;
    }


    public static final class Builder {
        private SourceType type;
        private String globalId;
        private String hostName;
        private int hostId;
        private String indirectId;

        private Builder() {
        }

        public Builder withType(SourceType val) {
            type = val;
            return this;
        }

        public Builder withGlobalId(String val) {
            globalId = val;
            return this;
        }

        public Builder withHostName(String val) {
            hostName = val;
            return this;
        }

        public Builder withHostId(int val) {
            hostId = val;
            return this;
        }

        public Builder withIndirectId(String val) {
            indirectId = val;
            return this;
        }

        public NetworkSource build() {
            return new NetworkSource(this);
        }
    }
}

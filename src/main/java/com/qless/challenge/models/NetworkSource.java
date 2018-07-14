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
}

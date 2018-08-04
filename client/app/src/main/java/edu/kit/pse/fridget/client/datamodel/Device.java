package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class Device {
    @SerializedName("id")
    private final String id;
    @SerializedName("userId")
    private final String userId;
    @SerializedName("instanceIdToken")
    private final String instanceIdToken;

    public Device(String id, String userId, String instanceIdToken) {
        this.id = id;
        this.userId = userId;
        this.instanceIdToken = instanceIdToken;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getInstanceIdToken() {
        return instanceIdToken;
    }
}

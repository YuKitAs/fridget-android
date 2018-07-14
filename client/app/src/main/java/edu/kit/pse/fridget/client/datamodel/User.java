package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private final String id;
    @SerializedName("googleUserId")
    private final String googleUserId;
    @SerializedName("googleName")
    private final String googleName;

    public User(String id, String googleUserId, String googleName) {
        this.id = id;
        this.googleUserId = googleUserId;
        this.googleName = googleName;
    }

    public String getId() {
        return id;
    }

    public String getGoogleUserId() {
        return googleUserId;
    }

    public String getGoogleName() {
        return googleName;
    }
}

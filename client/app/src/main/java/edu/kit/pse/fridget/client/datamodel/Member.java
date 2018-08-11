package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Member data model for a member of the flat share
 *
 * @author Alina Shah
 * @version 1.0
 */
public class Member {
    @SerializedName("id")
    private final String id;

    @SerializedName("userId")
    private final String userId;

    @SerializedName("flatshareId")
    private final String flatshareId;

    @SerializedName("magnetColor")
    private final String magnetColor;

    public Member(String id, String userId, String flatshareId, String magnetColor) {
        this.id = id;
        this.userId = userId;
        this.flatshareId = flatshareId;
        this.magnetColor = magnetColor;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFlatshareId() {
        return flatshareId;
    }

    public String getMagnetColor() {
        return magnetColor;
    }
}

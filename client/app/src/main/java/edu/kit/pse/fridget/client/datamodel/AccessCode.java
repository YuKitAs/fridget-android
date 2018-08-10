package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class AccessCode {
    @SerializedName("id")
    private final String id;

    @SerializedName("content")
    private final String content;

    @SerializedName("flatshareId")
    private final String flatshareId;

    public AccessCode(String id, String content, String flatshareId) {
        this.id = id;
        this.content = content;
        this.flatshareId = flatshareId;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getFlatshareId() {
        return flatshareId;
    }
}

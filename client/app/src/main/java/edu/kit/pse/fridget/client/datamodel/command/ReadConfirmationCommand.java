package edu.kit.pse.fridget.client.datamodel.command;

import com.google.gson.annotations.SerializedName;

public class ReadConfirmationCommand {

    @SerializedName("id")
    private String id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("flatshareId")
    private String flatshareId;

    @SerializedName("magnetColor")
    private String magnetColor;

    public ReadConfirmationCommand(String id, String userId, String flatshareId, String magnetColor) {
        this.id = id;
        this.userId = userId;
        this.flatshareId = flatshareId;
        this.magnetColor = magnetColor;
    }

    public String getId(){
        return id;
    }

    public String getUserId() { return  userId; }

    public String getFlatshareId() { return flatshareId; }

    public String getMagnetColor() { return magnetColor; }

}

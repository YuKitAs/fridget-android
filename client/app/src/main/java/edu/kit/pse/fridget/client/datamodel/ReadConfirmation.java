package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class ReadConfirmation {

    @SerializedName("id")
    private String id;

    @SerializedName("coolNoteId")
    private String coolNoteId;

    @SerializedName("membershipId")
    private String membershipId;

    public String getId(){
        return id;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getCoolNoteId() {
        return coolNoteId;
    }

}


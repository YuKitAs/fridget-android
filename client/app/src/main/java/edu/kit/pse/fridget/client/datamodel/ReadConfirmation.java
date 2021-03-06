package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class ReadConfirmation {
    @SerializedName("id")
    private final String id;

    @SerializedName("coolNoteId")
    private final String coolNoteId;

    @SerializedName("membershipId")
    private final String membershipId;

    public ReadConfirmation(String id, String coolNoteId, String membershipId) {
        this.id = id;
        this.coolNoteId = coolNoteId;
        this.membershipId = membershipId;
    }

    public String getId() {
        return id;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getCoolNoteId() {
        return coolNoteId;
    }
}


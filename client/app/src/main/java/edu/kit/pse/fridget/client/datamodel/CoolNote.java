package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Cool Note data model for a Text-Cool-Note (TCN)
 *
 * @author Alina Shah
 * @version 1.0
 */
public class CoolNote {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("creatorMembershipId")
    private String creatorMembershipId;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("position")
    private int position;

    @SerializedName("importance")
    private String importance;

    @SerializedName("taggedMembershipIds")
    private String taggedMembershipIds;

    public CoolNote(String id, String title, String content, String creatorUserId, String createdAt, int position, String importance, String taggedMembershipIds){
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatorMembershipId = creatorUserId;
        this.createdAt = createdAt;
        this.position = position;
        this.importance = importance;
        this.taggedMembershipIds = taggedMembershipIds;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatorUserId() {
        return creatorMembershipId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getPosition() {
        return this.position;
    }

    // brauchen wir? hab es erstmal zum testen gelassen...
    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

}

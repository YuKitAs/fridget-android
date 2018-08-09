package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private int importance;

    @SerializedName("taggedMembershipIds")
    private List<String> taggedMembershipIds;

    public CoolNote(String id, String title, String content, String creatorMembershipId, int position, int importance, String createdAt,
                    List<String> taggedMembershipIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatorMembershipId = creatorMembershipId;
        this.position = position;
        this.importance = importance;
        this.createdAt = createdAt;
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

    public String getCreatorMembershipId() {
        return creatorMembershipId;
    }

    public int getPosition() {
        return position;
    }

    public int getImportance() {
        return importance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<String> getTaggedMembershipIds() {
        return taggedMembershipIds;
    }
}

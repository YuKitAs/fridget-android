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
    private final String id;

    @SerializedName("title")
    private final String title;

    @SerializedName("content")
    private final String content;

    @SerializedName("creatorMembershipId")
    private final String creatorMembershipId;

    @SerializedName("createdAt")
    private final String createdAt;

    @SerializedName("position")
    private final int position;

    @SerializedName("importance")
    private final int importance;

    @SerializedName("taggedMembershipIds")
    private final List<String> taggedMembershipIds;

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

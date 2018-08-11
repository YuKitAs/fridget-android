package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class FrozenNote {
    @SerializedName("id")
    private final String id;

    @SerializedName("title")
    private final String title;

    @SerializedName("content")
    private final String content;

    @SerializedName("flatshareId")
    private final String flatshareId;

    @SerializedName("position")
    private final int position;

    /**
     * Getter for the ID
     *
     * @return id
     */
    public FrozenNote(String id, String title, String content, String flatshareId, int position) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.flatshareId = flatshareId;
        this.position = position;
    }


    public String getId() {
        return id;
    }

    /**
     * Getter for the title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the content
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for the flatShareId
     *
     * @return flatShareId
     */
    public String getFlatshareId() {
        return flatshareId;
    }

    /**
     * Getter for position of Frozen Note
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }
}



package edu.kit.pse.fridget.client.datamodel;

/**
 * Cool Note data model for a Text-Cool-Note (TCN)
 *
 * @author Alina Shah
 * @version 1.0
 */
public class CoolNote {

    private String id;
    private String title;
    private String content;
    private String flatShareId;
    private String creatorUserId;
    private String createdAt;
    private int position;

    public CoolNote(String id, String title, String content, String flatShareId, String creatorUserId, String createdAt, int position){
        this.id = id;
        this.title = title;
        this.content = content;
        this. flatShareId = flatShareId;
        this.creatorUserId = creatorUserId;
        this.createdAt = createdAt;
        this.position = position;
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

    public String getFlatShareId() {
        return flatShareId;
    }

    public String getCreatorUserId() {
        return creatorUserId;
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
}

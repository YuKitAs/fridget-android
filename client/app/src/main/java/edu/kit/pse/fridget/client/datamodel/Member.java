package edu.kit.pse.fridget.client.datamodel;

/**
 * Member data model for a member of the flat share
 *
 * @author Alina Shah
 * @version 1.0
 */
public class Member {

    private String id;
    private String userId;
    private String flatshareId;
    private String magnetColor;

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

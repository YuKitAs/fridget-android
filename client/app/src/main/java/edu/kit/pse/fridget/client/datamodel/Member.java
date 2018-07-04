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
    private String flatShareId;
    private String magnetColor;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFlatShareId() {
        return flatShareId;
    }

    public String getMagnetColor() {
        return magnetColor;
    }

}

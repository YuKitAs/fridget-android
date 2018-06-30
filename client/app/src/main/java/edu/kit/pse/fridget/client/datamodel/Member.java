package edu.kit.pse.fridget.client.datamodel;

/**
 * Member data model for a member of the flat share
 *
 * @author Alina Shah
 * @version 1.0
 */
public class Member {

    /**
     * Id of the member
     */
    private String id;

    /**
     * Id of the user
     */
    private String userId;

    /**
     * Id of the flatshare
     */
    private String flatShareId;

    /**
     * Color of the member's magnet
     */
    private String magnetColor;

    /**
     * Getter for the id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the userId
     *
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Getter for the flatShareId
     *
     * @return flatShareId
     */
    public String getFlatShareId() {
        return flatShareId;
    }

    /**
     * Getter for the magnetColor
     *
     * @return magnetColor
     */
    public String getMagnetColor() {
        return magnetColor;
    }

}

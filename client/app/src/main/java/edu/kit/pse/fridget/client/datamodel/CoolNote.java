package edu.kit.pse.fridget.client.datamodel;

/**
 * Cool Note data model for a Text-Cool-Note (TCN)
 *
 * @author Alina Shah
 * @version 1.0
 */
public class CoolNote {


    /**
     * Id of the TCN
     */
    private String id;

    /**
     * Title of the TCN
     */
    private String title;
    /**
     * Content of the TCN
     */
    private String content;

    /**
     * Id of the flat share this
     */
    private String flatShareId;

    /**
     * Id of the User who created this TCN
     */
    private String creatorUserId;

    /**
     * Date on which the TCN was created
     */
    private String createdAt;

    /**
     * Position of the TCN on the pin board
     */
    private int position;

    /**
     * Getter for the id
     *
     * @return id
     */
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
    public String getFlatShareId() {
        return flatShareId;
    }

    /**
     * Getter for the creatorUserId
     *
     * @return creatorUserId
     */
    public String getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * Getter for the createdAt
     *
     * @return createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

}

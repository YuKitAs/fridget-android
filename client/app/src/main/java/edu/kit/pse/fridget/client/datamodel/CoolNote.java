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

}

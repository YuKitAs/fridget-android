package edu.kit.pse.fridget.client.datamodel;

public class FrozenNote {
    /**
     * ID of the Frozen Note
     */
    private String id;

    /**
     * Title of the Frozen Note
     */
    private String title;
    /**
     * Content of the Frozen Note
     */
    private String content;

    /**
     * ID of the Flatshare the Frozen Note belongs to
     */
    private String flatShareId;

    /**
     * Position of the Frozen Note on the pinboard
     */
    private int position;

    /**
     * Getter for the ID
     *
     * @return id
     */

    public FrozenNote(String id, String title, String content, String flatShareId, int position){
        this.id = id;
        this.title = title;
        this.content = content;
        this.flatShareId = flatShareId;
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
    public String getFlatShareId() {
        return flatShareId;
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



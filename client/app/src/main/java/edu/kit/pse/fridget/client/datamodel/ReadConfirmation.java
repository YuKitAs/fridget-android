package edu.kit.pse.fridget.client.datamodel;

public class ReadConfirmation {

    private String readConfirmationId;

    private String coolNoteId;

    private String userId;

    public String getId(){
        return readConfirmationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCoolNoteId() {
        return coolNoteId;
    }

}


package edu.kit.pse.fridget.client.datamodel.command;

public class EnterFlatshareCommand {
    private final String accessCode;
    private final String userId;


    public EnterFlatshareCommand(String accessCode, String userId) {
        this.accessCode = accessCode;
        this.userId = userId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getUserId() {
        return userId;
    }
}

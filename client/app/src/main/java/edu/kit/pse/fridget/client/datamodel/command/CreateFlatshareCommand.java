package edu.kit.pse.fridget.client.datamodel.command;

public class CreateFlatshareCommand {
    private final String flatshareName;
    private final String userId;

    public CreateFlatshareCommand(String flatshareName, String userId) {
        this.flatshareName = flatshareName;
        this.userId = userId;
    }

    public String getFlatshareName() {
        return flatshareName;
    }

    public String getUserId() {
        return userId;
    }
}

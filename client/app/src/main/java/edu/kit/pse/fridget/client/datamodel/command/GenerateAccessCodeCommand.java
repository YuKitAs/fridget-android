package edu.kit.pse.fridget.client.datamodel.command;

public class GenerateAccessCodeCommand {
    private final String flatshareId;

    public GenerateAccessCodeCommand(String flatshareId) {
        this.flatshareId = flatshareId;
    }

    public String getFlatshareId() {
        return flatshareId;
    }
}

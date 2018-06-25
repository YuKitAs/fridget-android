package edu.kit.pse.fridget.server.models.commands;

import edu.kit.pse.fridget.server.models.Membership;

public class SaveMembershipCommand {
    private final String accessCode;
    private final String userId;

    public SaveMembershipCommand(String accessCode, String userId) {
        this.accessCode = accessCode;
        this.userId = userId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getUserId() {
        return userId;
    }

    public Membership.Builder getBuilder() {
        return new Membership.Builder();
    }
}

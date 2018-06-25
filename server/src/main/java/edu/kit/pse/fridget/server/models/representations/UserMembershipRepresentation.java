package edu.kit.pse.fridget.server.models.representations;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.User;

public class UserMembershipRepresentation {
    private final User user;
    private final Membership membership;

    public UserMembershipRepresentation(User user, Membership membership) {
        this.user = user;
        this.membership = membership;
    }

    public String getUserId() {
        return user.getId();
    }

    public String getGoogleUserId() {
        return user.getGoogleUserId();
    }

    public String getGoogleName() {
        return user.getGoogleName();
    }

    public String getMagnetColor() {
        return membership.getMagnetColor();
    }
}

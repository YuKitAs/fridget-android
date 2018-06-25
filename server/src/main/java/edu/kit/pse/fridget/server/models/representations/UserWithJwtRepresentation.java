package edu.kit.pse.fridget.server.models.representations;

import edu.kit.pse.fridget.server.models.User;

public class UserWithJwtRepresentation {
    private final User user;
    private final String jwt;

    public UserWithJwtRepresentation(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }
}

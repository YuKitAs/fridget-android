package edu.kit.pse.fridget.client.datamodel.representation;

import edu.kit.pse.fridget.client.datamodel.User;

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

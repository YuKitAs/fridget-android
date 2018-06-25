package edu.kit.pse.fridget.server.services;

import edu.kit.pse.fridget.server.models.representations.UserWithJwtRepresentation;

public interface UserService {
    UserWithJwtRepresentation registerOrLogin(String googleIdToken);
}

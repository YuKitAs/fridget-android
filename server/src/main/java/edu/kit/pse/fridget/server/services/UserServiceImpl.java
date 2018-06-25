package edu.kit.pse.fridget.server.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kit.pse.fridget.server.models.User;
import edu.kit.pse.fridget.server.models.representations.UserWithJwtRepresentation;
import edu.kit.pse.fridget.server.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public UserWithJwtRepresentation registerOrLogin(String googleIdToken) {
        GoogleIdToken.Payload payload = new AuthenticationService().verifyIdTokenAndGetPayload(googleIdToken);
        String googleUserId = payload.getSubject();
        String googleName = (String) payload.get("name");

        return repository.findByGoogleUserId(googleUserId)
                .map(userFound -> new UserWithJwtRepresentation(userFound, "JWT"))
                .orElseGet(() -> register(googleUserId, googleName));
    }

    private UserWithJwtRepresentation register(String googleUserId, String googleName) {
        User newUser = User.buildNew(googleUserId, googleName);
        repository.save(newUser);

        return new UserWithJwtRepresentation(newUser, "JWT");
    }
}

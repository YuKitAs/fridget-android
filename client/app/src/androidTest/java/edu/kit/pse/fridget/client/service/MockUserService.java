package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockUserService implements UserService {
    private User user =new User ("testId","testGoogleUserId","testGoogleName");
    private final BehaviorDelegate<UserService> delegate;

    public MockUserService (BehaviorDelegate<UserService>service) {
        this.delegate =service;
    }


    @Override
    public Call<UserWithJwtRepresentation> sendIdToken(String idToken) {
        UserWithJwtRepresentation response = new UserWithJwtRepresentation(user,"testJWT");
        return delegate.returningResponse(response).sendIdToken(idToken);

    }
}

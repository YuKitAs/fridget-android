package edu.kit.pse.fridget.client.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.command.GenerateAccessCodeCommand;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockAccessCodeService implements AccessCodeService {
    private final BehaviorDelegate<AccessCodeService> delegate;

    public MockAccessCodeService (BehaviorDelegate<AccessCodeService>service) {

        this.delegate =service;


    }

    @Override
    public Call<AccessCode> generateAccessCode(GenerateAccessCodeCommand generateAccessCodeCommand) {
       AccessCode response = new AccessCode("testId","testContent","flatshareId");
        return delegate.returningResponse(response).generateAccessCode(generateAccessCodeCommand);
    }
}

package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockFlatshareService implements FlatshareService {


    private final BehaviorDelegate<FlatshareService> delegate;

    public MockFlatshareService(BehaviorDelegate<FlatshareService> service) {

        this.delegate =service;
    }
    @Override
    public Call<Flatshare> createFlatshare(CreateFlatshareCommand createFlatshareCommand) {

        Flatshare response = new Flatshare("testId","testName");

        return delegate.returningResponse(response).createFlatshare(createFlatshareCommand);
    }

    @Override
    public Call<Flatshare> getFlatshare(String flatshareId) {
        Flatshare response = new Flatshare("testId","testName");
    return delegate.returningResponse(response).getFlatshare(flatshareId);


    }
}

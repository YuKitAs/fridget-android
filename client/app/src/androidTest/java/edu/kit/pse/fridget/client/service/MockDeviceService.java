package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Device;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockDeviceService implements DeviceService {
    private final BehaviorDelegate<DeviceService> delegate;

    public MockDeviceService (BehaviorDelegate<DeviceService>service) {

        this.delegate =service;

    }


    @Override
    public Call<Device> sendDevice(Device device) {
       Device response = new Device("testId","testUserId","testInstanceToken");
       return delegate.returningResponse(response).sendDevice(device);
    }
}

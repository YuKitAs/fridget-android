package edu.kit.pse.fridget.client.service;

import android.test.InstrumentationTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class ReadConfirmationServiceTest extends InstrumentationTestCase {

    private MockRetrofit mockRetrofit;
    public Retrofit retrofit;
    private CoolNote coolNote = new CoolNote("testId", "testTitle", "testContent", "testId2", 0, 0, null, Collections.emptyList());


    @Override
    public void setUp() throws Exception{
        super.setUp();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }


    @Test
    public void testGetReaders() throws Exception {
        BehaviorDelegate<ReadConfirmationService> delegate = mockRetrofit.create(ReadConfirmationService.class);
        ReadConfirmationService readConfirmationService = new MockReadConfirmationService(delegate);

        //Actual Test
        Call<List<Member>> readers = readConfirmationService.getReaders(coolNote.getId());
        Response<List<Member>> response = readers.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testId1", response.body().get(0).getId());
        Assert.assertEquals("testFlatshareId", response.body().get(0).getFlatshareId());
        Assert.assertEquals("testUserId1", response.body().get(0).getUserId());
        Assert.assertEquals("#123456", response.body().get(0).getMagnetColor());

    }

    @Test
    public void testCreateReadStatus() throws Exception {
        BehaviorDelegate<ReadConfirmationService> delegate = mockRetrofit.create(ReadConfirmationService.class);
        ReadConfirmationService readConfirmationService = new MockReadConfirmationService(delegate);
        ReadConfirmation readConfirmation = new ReadConfirmation("testId", "testCoolNoteId", "testId1");

        //Actual Test
        Call<ReadConfirmation> reader = readConfirmationService.createReadStatus(readConfirmation);
        Response<ReadConfirmation> response = reader.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testId", response.body().getId());
        Assert.assertEquals("testCoolNoteId", response.body().getCoolNoteId());
        Assert.assertEquals("testId1", response.body().getMembershipId());
    }

    @Test
    public void testDeleteReadStatus() throws Exception {
        BehaviorDelegate<ReadConfirmationService> delegate = mockRetrofit.create(ReadConfirmationService.class);
        ReadConfirmationService readConfirmationService = new MockReadConfirmationService(delegate);
        ReadConfirmation readConfirmation = new ReadConfirmation("testId", "testCoolNoteId", "testId1");

        //Actual Test
        Call<Void> delete = readConfirmationService.deleteReadStatus(coolNote.getId(), "testId1");
        Response<Void> response = delete.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        assertEquals(null, response.body());
    }

}

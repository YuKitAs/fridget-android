package edu.kit.pse.fridget.client.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class MembershipServiceTest  {
    MockRetrofit mockRetrofit;
    Retrofit retrofit;
    String testFlatshareId = "testFlatshareId";
    String testUserId = "testUserId";
    EnterFlatshareCommand testEnterFlatshareCommand =new EnterFlatshareCommand("testAccesscode", testUserId);

    @Before
    public void setUp() throws Exception {


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
    public void testGetMemberList() throws IOException {
        String testFlatshareId = "testFlatshareId";
        BehaviorDelegate<MembershipService> delegate = mockRetrofit.create(MembershipService.class);
        MembershipService membershipService = new MockMembershipService(delegate);

        //Actual Test
        Call<List<UserMembershipRepresentation>> quote = membershipService.getMemberList(testFlatshareId);
        Response<List<UserMembershipRepresentation>> response =quote.execute();

        //Asserting response
       Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testGoogleName", response.body().get(0).getGoogleName());
        Assert.assertEquals("testcolor", response.body().get(0).getMagnetColor());
        Assert.assertEquals("testMemberId", response.body().get(0).getMemberId());
        Assert.assertEquals("testGoogleName_1", response.body().get(1).getGoogleName());
        Assert.assertEquals("testcolor_1", response.body().get(1).getMagnetColor());
        Assert.assertEquals("testMemberId_1", response.body().get(1).getMemberId());
    }

    @Test
    public void testGetMember() throws IOException {
        String testFlatshareId = "testFlatshareId";
        String testUserId = "testUserId";
        BehaviorDelegate<MembershipService> delegate = mockRetrofit.create(MembershipService.class);
        MembershipService membershipService = new MockMembershipService(delegate);
        //Actual Test
        Call<UserMembershipRepresentation> quote = membershipService.getMember(testFlatshareId,testUserId);
        Response<UserMembershipRepresentation> response =quote.execute();
        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testMemberId", response.body().getMemberId());
        Assert.assertEquals("testcolor",response.body().getMagnetColor());
        Assert.assertEquals("testGoogleName",response.body().getGoogleName());
    }

    @Test
    public void testCreateMembership() throws IOException {

        BehaviorDelegate<MembershipService> delegate = mockRetrofit.create(MembershipService.class);
        MembershipService membershipService = new MockMembershipService(delegate);
        //Actual Test
        Call<Member> quote = membershipService.createMembership(testEnterFlatshareCommand);
        Response<Member> response =quote.execute();
        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        org.junit.Assert.assertEquals("testFlatshareId", response.body().getFlatshareId());
        org.junit.Assert.assertEquals("testId", response.body().getId());
        org.junit.Assert.assertEquals("testMagnetColor", response.body().getMagnetColor());
        org.junit.Assert.assertEquals("testUserId",response.body().getUserId());
    }

    @Test
    public void testDeleteMember() throws IOException {
        String testFlatshareId = "testFlatshareId";
        String testUserId = "testUserId";
        BehaviorDelegate<MembershipService> delegate = mockRetrofit.create(MembershipService.class);
        MembershipService membershipService = new MockMembershipService(delegate);
        //Actual Test
        Call<Void> quote = membershipService.deleteMember(testFlatshareId,testUserId);
        Response<Void> response =quote.execute();
        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        org.junit.Assert.assertEquals(null, response.body());
    }
}
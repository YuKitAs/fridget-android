package edu.kit.pse.fridget.client.service;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class MembershipServiceTest {

    UserMembershipRepresentation userMembershipRepresentation = new UserMembershipRepresentation("testMemberId","testcolor","testGoogleName");
    String testFlatshareId ="123";
    String testUserId ="456";

    EnterFlatshareCommand testEnterFlatshareCommand =new EnterFlatshareCommand("testAccesscode", testUserId);
    Member testMember =new Member("testId",testUserId,testFlatshareId,"testMagnetColor");

    List<UserMembershipRepresentation> testUserList = new ArrayList<UserMembershipRepresentation>() ;


    MockWebServer server = new MockWebServer();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MembershipService service = retrofit.create(MembershipService.class);
    @Test
    public void getMemberList() throws IOException {
        server.enqueue(new MockResponse().setBody(new Gson().toJson(testUserList)));
        Call<List<UserMembershipRepresentation>> call = service.getMemberList(testFlatshareId);
        assertTrue(call.execute() != null);
        server.shutdown();
    }

    @Test
    public void getMember() throws IOException {
        server.enqueue(new MockResponse().setBody(new Gson().toJson(userMembershipRepresentation)));

        Call<UserMembershipRepresentation> call =service.getMember(testFlatshareId,testUserId);
        assertTrue(call.execute() != null);
        server.shutdown();

    }

    @Test
    public void createMembership() throws IOException {

        server.enqueue(new MockResponse().setBody(new Gson().toJson(testMember)));
        Call<Member> call =service.createMembership(testEnterFlatshareCommand);
        assertTrue(call.execute() != null);
        server.shutdown();
    }

    @Test
    public void deleteMember() throws IOException {
        server.enqueue(new MockResponse().setBody(new Gson().toJson(null)));
        Call<Void> call =service.deleteMember(testFlatshareId,testUserId);
        assertTrue(call.execute() != null);
        server.shutdown();
    }
}
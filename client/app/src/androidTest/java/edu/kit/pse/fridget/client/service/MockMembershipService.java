package edu.kit.pse.fridget.client.service;

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockMembershipService implements MembershipService {


    private final BehaviorDelegate<MembershipService> delegate;

    public MockMembershipService (BehaviorDelegate<MembershipService>service) {

        this.delegate =service;


    }
    @Override
    public Call<List<UserMembershipRepresentation>> getMemberList(String flatshareId) {

        UserMembershipRepresentation userMembershipRepresentation = new UserMembershipRepresentation("testMemberId","testcolor","testGoogleName");
         UserMembershipRepresentation userMembershipRepresentation_1 = new UserMembershipRepresentation("testMemberId_1","testcolor_1","testGoogleName_1");
        List<UserMembershipRepresentation> response = new ArrayList<>() ;
        response.add(0,userMembershipRepresentation);
        response.add(1,userMembershipRepresentation_1);
        return delegate.returningResponse(response).getMemberList(flatshareId);
    }

    @Override

    public Call<UserMembershipRepresentation> getMember(String flatshareId, String userId) {
        UserMembershipRepresentation userMembershipRepresentation = new UserMembershipRepresentation("testMemberId","testcolor","testGoogleName");
      return delegate.returningResponse(userMembershipRepresentation).getMember(flatshareId,userId);
    }

    @Override

    public Call<Member> createMembership(EnterFlatshareCommand enterFlatshareCommand) {
        Member testMember =new Member("testId","testUserId","testFlatshareId","testMagnetColor");
        return  delegate.returningResponse(testMember).createMembership(enterFlatshareCommand);
    }

    @Override
    public Call<Void> deleteMember(String flatshareId, String userId) {

        return delegate.returningResponse(null).deleteMember(flatshareId,userId);
    }
}

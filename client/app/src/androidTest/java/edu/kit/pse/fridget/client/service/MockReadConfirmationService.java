package edu.kit.pse.fridget.client.service;

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockReadConfirmationService implements ReadConfirmationService {

    private final BehaviorDelegate<ReadConfirmationService> delegate;
    private String flatshareId = "testFlatshareId";
    private String coolNoteId = "testCoolNoteId";

    public MockReadConfirmationService(BehaviorDelegate<ReadConfirmationService> service) {
        this.delegate =service;
    }


    @Override
    public Call<List<Member>> getReaders(String coolNoteId) {
        coolNoteId = this.coolNoteId;
        List<Member> response = new ArrayList<Member>();
        Member member1 = new Member("testId1", "testUserId1", flatshareId, "#123456");
        Member member2 = new Member("testId2", "testUserId2", flatshareId, "#456789");
        response.add(member1);
        response.add(member2);
        return delegate.returningResponse(response).getReaders(coolNoteId);
    }

    @Override
    public Call<ReadConfirmation> createReadStatus(ReadConfirmation readConfirmation) {
        readConfirmation = new ReadConfirmation("testId", coolNoteId, "testId1");
        return delegate.returningResponse(readConfirmation).createReadStatus(readConfirmation);
    }


    @Override
    public Call<Void> deleteReadStatus(String coolNoteId, String membershipId) {
        coolNoteId = this.coolNoteId;
        membershipId = "testId1";
        Void response = null;
        return delegate.returningResponse(response).deleteReadStatus(coolNoteId, membershipId);
    }
}

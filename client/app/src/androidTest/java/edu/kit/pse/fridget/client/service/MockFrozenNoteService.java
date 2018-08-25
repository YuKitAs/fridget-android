package edu.kit.pse.fridget.client.service;

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockFrozenNoteService implements FrozenNoteService {
    private final BehaviorDelegate<FrozenNoteService> delegate;
    private String flatshareId = "testFlatshareId";

    public MockFrozenNoteService(BehaviorDelegate<FrozenNoteService> service) {
        this.delegate =service;
    }


    @Override
    public Call<FrozenNote> getFrozenNote(String frozenNoteId) {
        FrozenNote response = new FrozenNote(frozenNoteId, "testTitle","testContent","testFlatshareId",0);
        return delegate.returningResponse(response).getFrozenNote(frozenNoteId);
    }

    @Override
    public Call<List<FrozenNote>> getAllFrozenNote(String flatshareId) {
        flatshareId = this.flatshareId;
        FrozenNote frozenNote1 = new FrozenNote("testId1", "testTitle1","testContent1",flatshareId,0);
        FrozenNote frozenNote2 = new FrozenNote("testId2", "testTitle2","testConten2t",flatshareId,1);
        List<FrozenNote> response = new ArrayList<FrozenNote>();
        response.add(frozenNote1);
        response.add(frozenNote2);
        return delegate.returningResponse(response).getAllFrozenNote(flatshareId);
    }


    @Override
    public Call<FrozenNote> updateFrozenNote(String frozenNoteId, FrozenNote frozenNote) {
        frozenNote = new FrozenNote(frozenNoteId, "testTitle","testContent","testFlatshareId",0);
        return delegate.returningResponse(frozenNote).updateFrozenNote(frozenNoteId, frozenNote);
    }
}

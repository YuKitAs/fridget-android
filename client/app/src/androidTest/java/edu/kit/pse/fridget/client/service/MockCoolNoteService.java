package edu.kit.pse.fridget.client.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockCoolNoteService implements CoolNoteService {

    private final BehaviorDelegate<CoolNoteService> delegate;
    private String flatshareId = "testFlatshareId";

    public MockCoolNoteService(BehaviorDelegate<CoolNoteService> service) {
        this.delegate =service;
    }


    @Override
    public Call<CoolNote> getCoolNote(String coolNoteId) {
        CoolNote response = new CoolNote(coolNoteId,"testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        return delegate.returningResponse(response).getCoolNote(coolNoteId);
    }

    @Override
    public Call<List<CoolNote>> getAllCoolNotes(String flatshareId) {
        flatshareId = this.flatshareId;
        CoolNote coolNote1 = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        CoolNote coolNote2 = new CoolNote("testId2","testTitle2","testContent2", "testMemberId2", 1, 0, null, Collections.emptyList());
        List<CoolNote> response = new ArrayList<CoolNote>();
        response.add(coolNote1);
        response.add(coolNote2);
        return delegate.returningResponse(response).getAllCoolNotes(flatshareId);
    }


    @Override
    public Call<CoolNote> createCoolNote(CoolNote coolNote) {
        coolNote = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        return delegate.returningResponse(coolNote).createCoolNote(coolNote);
    }

    @Override
    public Call<Void> deleteCoolNote(String coolNoteId) {
        Void response = null;
        return delegate.returningResponse(response).deleteCoolNote(coolNoteId);
    }

}

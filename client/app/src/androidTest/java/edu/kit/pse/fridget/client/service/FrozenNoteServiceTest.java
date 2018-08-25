package edu.kit.pse.fridget.client.service;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FrozenNoteServiceTest {
    private MockRetrofit mockRetrofit;

    @Before
    public void setUp() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void testGetAllFrozenNotes() throws IOException {
        String flatshareId = "testFlatshareId";
        BehaviorDelegate<FrozenNoteService> delegate = mockRetrofit.create(FrozenNoteService.class);
        FrozenNoteService frozenNoteService = new MockFrozenNoteService(delegate);
        FrozenNote frozenNote1 = new FrozenNote("testId1", "testTitle1","testContent1",flatshareId,0);
        FrozenNote frozenNote2 = new FrozenNote("testId2", "testTitle2","testConten2t",flatshareId,1);
        List<FrozenNote> frozenNotes = new ArrayList<>();
        frozenNotes.add(frozenNote1);
        frozenNotes.add(frozenNote2);

        //Actual Test
        Call<List<FrozenNote>> quote = frozenNoteService.getAllFrozenNote(flatshareId);
        Response<List<FrozenNote>> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals(frozenNotes.get(0).getId(), response.body().get(0).getId());
        assertEquals(frozenNotes.get(1).getId(), response.body().get(1).getId());
    }

    @Test
    public void testGetFrozenNote() throws IOException {
        BehaviorDelegate<FrozenNoteService> delegate = mockRetrofit.create(FrozenNoteService.class);
        FrozenNoteService frozenNoteService = new MockFrozenNoteService(delegate);
        String frozenNoteId = "testId";
        FrozenNote frozenNote = new FrozenNote(frozenNoteId, "testTitle","testContent","testFlatshareId",0);

        //Actual Test
        Call<FrozenNote> quote = frozenNoteService.getFrozenNote(frozenNoteId);
        Response<FrozenNote> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals("testId", response.body().getId());
        assertEquals("testTitle", response.body().getTitle());
        assertEquals("testContent", response.body().getContent());
        assertEquals(0, response.body().getPosition());
        assertEquals("testFlatshareId", response.body().getFlatshareId());
    }


    @Test
    public void testUpdateFrozenNote() throws IOException {
        BehaviorDelegate<FrozenNoteService> delegate = mockRetrofit.create(FrozenNoteService.class);
        FrozenNoteService frozenNoteService = new MockFrozenNoteService(delegate);
        String frozenNoteId = "testId";
        FrozenNote frozenNote = new FrozenNote(frozenNoteId, "testTitle","testContent","testFlatshareId",0);


        //Actual Test
        Call<FrozenNote> quote = frozenNoteService.updateFrozenNote(frozenNoteId, frozenNote);
        Response<FrozenNote> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals("testId", response.body().getId());
        assertEquals("testTitle", response.body().getTitle());
        assertEquals("testContent", response.body().getContent());
        assertEquals(0, response.body().getPosition());
        assertEquals("testFlatshareId", response.body().getFlatshareId());
    }

}

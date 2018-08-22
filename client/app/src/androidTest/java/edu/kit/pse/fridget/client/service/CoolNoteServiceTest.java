package edu.kit.pse.fridget.client.service;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoolNoteServiceTest {

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
    public void testCreateCoolNote() throws IOException {
        BehaviorDelegate<CoolNoteService> delegate = mockRetrofit.create(CoolNoteService.class);
        CoolNoteService coolNoteService = new MockCoolNoteService(delegate);
        CoolNote coolNote = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());

        //Actual Test
        Call<CoolNote> quote = coolNoteService.createCoolNote(coolNote);
        Response<CoolNote> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals("testId", response.body().getId());
        assertEquals("testTitle", response.body().getTitle());
        assertEquals("testContent", response.body().getContent());
        assertEquals("testMemberId", response.body().getCreatorMembershipId());
        assertEquals(0, response.body().getPosition());
        assertEquals(0, response.body().getImportance());
        assertEquals(null, response.body().getCreatedAt());
        assertEquals(Collections.emptyList(), response.body().getTaggedMembershipIds());
    }

    @Test
    public void testGetAllCoolNotes() throws IOException {
        BehaviorDelegate<CoolNoteService> delegate = mockRetrofit.create(CoolNoteService.class);
        CoolNoteService coolNoteService = new MockCoolNoteService(delegate);
        CoolNote coolNote1 = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        CoolNote coolNote2 = new CoolNote("testId2","testTitle2","testContent2", "testMemberId2", 1, 0, null, Collections.emptyList());
        List<CoolNote> coolNotes = new ArrayList<>();
        coolNotes.add(coolNote1);
        coolNotes.add(coolNote2);
        String flatshareId = "testFlatshareId";

        //Actual Test
        Call<List<CoolNote>> quote = coolNoteService.getAllCoolNotes(flatshareId);
        Response<List<CoolNote>> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals(coolNotes.get(0).getId(), response.body().get(0).getId());
        assertEquals(coolNotes.get(1).getId(), response.body().get(1).getId());
    }

    @Test
    public void testGetCoolNote() throws IOException {
        BehaviorDelegate<CoolNoteService> delegate = mockRetrofit.create(CoolNoteService.class);
        CoolNoteService coolNoteService = new MockCoolNoteService(delegate);
        CoolNote coolNote = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        String coolNoteId = "testId";

        //Actual Test
        Call<CoolNote> quote = coolNoteService.getCoolNote(coolNoteId);
        Response<CoolNote> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals("testId", response.body().getId());
        assertEquals("testTitle", response.body().getTitle());
        assertEquals("testContent", response.body().getContent());
        assertEquals("testMemberId", response.body().getCreatorMembershipId());
        assertEquals(0, response.body().getPosition());
        assertEquals(0, response.body().getImportance());
        assertEquals(null, response.body().getCreatedAt());
        assertEquals(Collections.emptyList(), response.body().getTaggedMembershipIds());
    }

    @Test
    public void testGetCoolNote_WithIncorrectId() throws IOException {
        BehaviorDelegate<CoolNoteService> delegate = mockRetrofit.create(CoolNoteService.class);
        CoolNoteService coolNoteService = new MockCoolNoteService(delegate);
        CoolNote coolNote = new CoolNote("testId","testTitle","testContent", "testMemberId", 0, 0, null, Collections.emptyList());
        String coolNoteId = "incorrectId";

        //Actual Test
        Call<CoolNote> quote = coolNoteService.getCoolNote(coolNoteId);
        Response<CoolNote> response = quote.execute();

        //Asserting response
        assertThat(coolNote.getId().equals(response.body().getId())).isFalse();
    }

    @Test
    public void testDeleteCoolNote() throws IOException {
        BehaviorDelegate<CoolNoteService> delegate = mockRetrofit.create(CoolNoteService.class);
        CoolNoteService coolNoteService = new MockCoolNoteService(delegate);
        String coolNoteId = "testId";

        //Actual Test
        Call<Void> quote = coolNoteService.deleteCoolNote(coolNoteId);
        Response<Void> response = quote.execute();

        //Asserting response
        assertTrue(response.isSuccessful());
        assertEquals(null, response.body());
    }

}

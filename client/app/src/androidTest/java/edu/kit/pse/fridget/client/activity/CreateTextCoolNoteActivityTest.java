package edu.kit.pse.fridget.client.activity;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CreateTextCoolNoteActivityTest {

    @Rule
    public ActivityTestRule<CreateTextCoolNoteActivity> mActivityTestRule = new ActivityTestRule<>(CreateTextCoolNoteActivity.class);

    private CreateTextCoolNoteActivity createTextCoolNoteActivity = null;

    @Before
    public void setUp() throws Exception {
        createTextCoolNoteActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void TestLaunch() {
        // View view = createTextCoolNoteActivity.findViewById(R.id.createCoolNote);
        // assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        createTextCoolNoteActivity = null;
    }

}

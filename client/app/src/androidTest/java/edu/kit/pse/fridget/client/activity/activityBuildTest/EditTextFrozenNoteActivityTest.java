package edu.kit.pse.fridget.client.activity.activityBuildTest;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.EditTextFrozenNoteActivity;

import static org.junit.Assert.assertNotNull;

public class EditTextFrozenNoteActivityTest {
    @Rule
    public ActivityTestRule<EditTextFrozenNoteActivity> mActivityTestRule = new ActivityTestRule<>(EditTextFrozenNoteActivity.class);

    private EditTextFrozenNoteActivity editTextFrozenNoteActivity =null;

    @Before
    public void setUp() throws Exception {
        editTextFrozenNoteActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view = editTextFrozenNoteActivity.findViewById(R.id.editFrozen);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception{
        editTextFrozenNoteActivity = null;
    }
}

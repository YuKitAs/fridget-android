package edu.kit.pse.fridget.client.activity.activityBuildTest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
    public ActivityTestRule<EditTextFrozenNoteActivity> mActivityTestRule = new ActivityTestRule<EditTextFrozenNoteActivity>(EditTextFrozenNoteActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, EditTextFrozenNoteActivity.class);
            intent.putExtra("position",0);
            intent.putExtra("frozenNoteId", "testId");
            return intent;
        }};

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

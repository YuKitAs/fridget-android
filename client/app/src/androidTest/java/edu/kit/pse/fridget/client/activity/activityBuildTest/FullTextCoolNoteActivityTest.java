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
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;

import static org.junit.Assert.assertNotNull;

public class FullTextCoolNoteActivityTest {

    @Rule
    public ActivityTestRule<FullTextCoolNoteActivity> mActivityTestRule = new ActivityTestRule<FullTextCoolNoteActivity>(FullTextCoolNoteActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, FullTextCoolNoteActivity.class);
            intent.putExtra("coolNoteId","testId");
            return intent;
        }};
    private FullTextCoolNoteActivity fullTextCoolNoteActivity = null;

    @Before
    public void setUp() throws Exception {
        fullTextCoolNoteActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view = fullTextCoolNoteActivity.findViewById(R.id.fullCN);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        fullTextCoolNoteActivity = null;
    }

}

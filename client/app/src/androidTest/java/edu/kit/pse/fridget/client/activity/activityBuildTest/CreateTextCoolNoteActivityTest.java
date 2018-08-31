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
import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;

import static junit.framework.Assert.assertNotNull;

public class CreateTextCoolNoteActivityTest {

    @Rule
    public ActivityTestRule<CreateTextCoolNoteActivity> mActivityTestRule = new ActivityTestRule<CreateTextCoolNoteActivity>(CreateTextCoolNoteActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, CreateTextCoolNoteActivity.class);
            intent.putExtra("position",0);
            return intent;
    }};

    private CreateTextCoolNoteActivity createTextCoolNoteActivity = null;

    @Before
    public void setUp() throws Exception {
        createTextCoolNoteActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view = createTextCoolNoteActivity.findViewById(R.id.createCN);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        createTextCoolNoteActivity = null;
    }

}

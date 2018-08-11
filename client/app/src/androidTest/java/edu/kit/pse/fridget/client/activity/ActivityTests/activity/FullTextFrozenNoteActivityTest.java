package edu.kit.pse.fridget.client.activity.ActivityTests.activity;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;

import static org.junit.Assert.assertNotNull;

public class FullTextFrozenNoteActivityTest {
    @Rule
    public ActivityTestRule<FullTextFrozenNoteActivity> mActivityTestRule = new ActivityTestRule<>(FullTextFrozenNoteActivity.class);

    private FullTextFrozenNoteActivity fullFrozenNoteActivity =null;

    @Before
    public void setUp() throws Exception {
        fullFrozenNoteActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view =fullFrozenNoteActivity.findViewById(R.id.fullFrozen);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        fullFrozenNoteActivity =null;
    }
}

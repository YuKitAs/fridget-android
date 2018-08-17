package edu.kit.pse.fridget.client.activity.activityBuildTest;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;

import static org.junit.Assert.assertNotNull;

/**
 * Diese Klasse testet, ob EnterAccessCode gestartet wird
 */
public class EnterAccessCodeActivityTest {

    @Rule
    public ActivityTestRule<EnterAccessCodeActivity> mActivityTestRule = new ActivityTestRule<>(EnterAccessCodeActivity.class);

    private EnterAccessCodeActivity enterAccessCodeActivity =null;

    @Before
    public void setUp() throws Exception {
        enterAccessCodeActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void TestLaunch() {
        View view =enterAccessCodeActivity.findViewById(R.id.enter_access_code);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        enterAccessCodeActivity =null;
    }
}
package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static org.junit.Assert.assertNotNull;

/**
 * Diese Klasse testet, ob die HomeActivity gestartet wird
 */
public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private HomeActivity homeActivity =null;

    @Before
    public void setUp() throws Exception {
        homeActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view =homeActivity.findViewById(R.id.home);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        homeActivity =null;
    }
}
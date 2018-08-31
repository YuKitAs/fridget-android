package edu.kit.pse.fridget.client.activity.activityBuildTest;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;

import static org.junit.Assert.assertNotNull;

/**
 * Diese Klasse testet, ob CreatFlatshareActivity gestartet wird
 */
public class CreateFlatshareActivityTest {

    @Rule
    public ActivityTestRule<CreateFlatshareActivity> mActivityTestRule = new ActivityTestRule<>(CreateFlatshareActivity.class);

    private CreateFlatshareActivity createFlatshareActivity =null;

    @Before
    public void setUp() throws Exception {
        createFlatshareActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void TestLaunch() {
    View view =createFlatshareActivity.findViewById(R.id.createFlatshare);
    assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        createFlatshareActivity =null;
    }
}
package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.StartActivity;

import static org.junit.Assert.*;

/**
 * Diese Klasse testet, ob StartActivity gestartet wird
 */
public class StartActivityTest {

    @Rule
    public ActivityTestRule<StartActivity> mStartActivityTestRule =new ActivityTestRule<>(StartActivity.class);

    private StartActivity startActivity =null;

    @Before
    public void setUp() throws Exception {
       startActivity =mStartActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch(){
        View view =startActivity.findViewById(R.id.start);

    }

    @After
    public void tearDown() throws Exception {
        startActivity=null;
    }
}
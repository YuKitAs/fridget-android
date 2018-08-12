package edu.kit.pse.fridget.client.activity;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;

import static org.junit.Assert.assertNotNull;
/**
 * Diese Klasse testet, ob LoginActivity gestartet wird
 */
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity loginActivity =null;

    @Before
    public void setUp() throws Exception {
        loginActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void TestLaunch() {
        View view =loginActivity.findViewById(R.id.loginId);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        loginActivity =null;
    }
}
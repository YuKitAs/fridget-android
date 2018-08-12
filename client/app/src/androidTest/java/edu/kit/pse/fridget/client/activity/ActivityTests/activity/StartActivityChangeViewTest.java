package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static edu.kit.pse.fridget.client.R.id.button4;

import static edu.kit.pse.fridget.client.R.id.button5;

import static org.junit.Assert.*;

/**
 * Diese Klasse testet ob ein Viewwechsel zu CreateFlatshareActivity  und zu EnterAccesscode stattfindet wenn man in der StartActivity auf RegisterYourFlatshare bzw auf EnterAcesscode  und Enter Accesscode klickt
 */
public class StartActivityChangeViewTest {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestrule = new ActivityTestRule<StartActivity>(StartActivity.class);

    private StartActivity startActivity =null;


    Instrumentation.ActivityMonitor monitor_flatshareregister = getInstrumentation().addMonitor(CreateFlatshareActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor_accescode = getInstrumentation().addMonitor(EnterAccessCodeActivity.class.getName(),null,false);
    @Before

        public void setUp() throws Exception {

        startActivity=mActivityTestrule.getActivity();
    }

    @Test
    public void testOnButtonRegisterFlatshareClick(){

      onView(withId(button4)).perform(click());

       Activity createFlatshareActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_flatshareregister,5000);
        assertNotNull(createFlatshareActivity);
        createFlatshareActivity.finish();
    }
    @Test
    public void testOnButtonEnterAccessCode(){

        onView(withId(button5)).perform(click());

        Activity enterAccesscodeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_accescode,5000);
        assertNotNull(enterAccesscodeActivity);
        enterAccesscodeActivity.finish();
    }

    @After
    public void tearDown() throws Exception {

        startActivity =null;
    }


}
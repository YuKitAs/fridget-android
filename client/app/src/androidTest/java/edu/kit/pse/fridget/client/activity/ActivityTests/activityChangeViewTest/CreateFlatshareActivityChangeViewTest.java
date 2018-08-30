package edu.kit.pse.fridget.client.activity.ActivityTests.activityChangeViewTest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static edu.kit.pse.fridget.client.R.id.button4;
import static edu.kit.pse.fridget.client.R.id.create;
import static edu.kit.pse.fridget.client.R.id.flatsharename_input;
import static org.junit.Assert.*;

public class CreateFlatshareActivityChangeViewTest {

    @Rule
    public ActivityTestRule<CreateFlatshareActivity> mActivityTestrule = new ActivityTestRule<CreateFlatshareActivity>(CreateFlatshareActivity.class);

    private CreateFlatshareActivity createFlatshareActivity =null;

    Instrumentation.ActivityMonitor monitor_home =
            getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {

        createFlatshareActivity = mActivityTestrule.getActivity();
    }

    @Test
    public void clickOnCreate() {
        //textinput
        onView(withId(flatsharename_input)).perform(typeText("testName"),closeSoftKeyboard());
        onView(withId(create)).perform(click());
        Activity createFlatshareActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_home,5000);
        assertNotNull(createFlatshareActivity);



        createFlatshareActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
    }
}
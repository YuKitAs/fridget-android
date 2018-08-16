package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;
import edu.kit.pse.fridget.client.fragment.CreateAccessCodeFragment;
import edu.kit.pse.fridget.client.viewmodel.SharedPreferencesData;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static edu.kit.pse.fridget.client.R.id.back_button;
import static edu.kit.pse.fridget.client.R.id.button4;
import static edu.kit.pse.fridget.client.R.id.button5;
import static edu.kit.pse.fridget.client.R.id.start;
import static org.junit.Assert.assertNotNull;


/**
 * Diese Klasse testet Viewwechsel von der CreateFlatshareActivity aus
 */
public class StartActivityChangeViewTest {

    @Rule
  public ActivityTestRule<StartActivity> mActivityTestrule = new ActivityTestRule<StartActivity>(StartActivity.class);

    private StartActivity startActivity =null;
    Instrumentation.ActivityMonitor monitor_createFlatshare =
            getInstrumentation().addMonitor(CreateFlatshareActivity.class.getName(),null,false);

    Instrumentation.ActivityMonitor monitor_home =
            getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    Instrumentation.ActivityMonitor monitor_enterAccesscode =
            getInstrumentation().addMonitor(EnterAccessCodeActivity.class.getName(),null,false);




    @Before
    public void setUp() throws Exception {

        startActivity = mActivityTestrule.getActivity();
    }

    /**
     Wenn noch keine Flatshare vorhanden dann Viewwechseö von Start zu CreateFlatshareActivity
     andernfalls direkt in home
     */
    @Test
    public void clickOnRegisterYourFlatshare() {
        SharedPreferences sharedPreferences =getInstrumentation().getTargetContext().getSharedPreferences("edu.kit.pse.fridget.client_preferences",Context.MODE_PRIVATE);
        String flatshareId =sharedPreferences.getString("flatshareId","N/A");

        if (flatshareId != "N/A"){

        Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_home,5000);
        assertNotNull(homeActivity);
        homeActivity.finish();}
        else {
            onView(withId(button4)).perform(click());
            Activity createFlatshareActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_createFlatshare,5000);
            assertNotNull(createFlatshareActivity);
            createFlatshareActivity.finish();
        }

    }

    /**
     Wenn noch keine Flatshare vorhanden dann Viewwechseö von Start zu EnterAccesscodeActivity
     andernfalls direkt in Home
     */

    @Test
    public void clickOnEnterAccesscode() {
        SharedPreferences sharedPreferences =getInstrumentation().getTargetContext().getSharedPreferences("edu.kit.pse.fridget.client_preferences",Context.MODE_PRIVATE);
        String flatshareId =sharedPreferences.getString("flatshareId","N/A");

        if (flatshareId != "N/A"){

            Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_home,5000);
            assertNotNull(homeActivity);
            homeActivity.finish();}
        else {
           onView(withId(button5)).perform(click());
            Activity enterAccesscodeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_enterAccesscode,5000);
            assertNotNull(enterAccesscodeActivity);
            enterAccesscodeActivity.finish();
        }

    }

    @After
    public void tearDown() throws Exception {
        startActivity =null;
    }
}

package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static edu.kit.pse.fridget.client.R.id.plus_button;
import static org.junit.Assert.assertNotNull;


/**
 * Diese Klasse testet Viewwechsel von der HomeActivity aus
 */
public class HomeActivityChangeViewTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestrule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

    private HomeActivity homeActivity =null;

    Instrumentation.ActivityMonitor monitor_createtextCoolNote =
            getInstrumentation().addMonitor(CreateTextCoolNoteActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor_fullTextCoolNote =
            getInstrumentation().addMonitor(FullTextCoolNoteActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor_fullTextFrozenNote =
            getInstrumentation().addMonitor(FullTextFrozenNoteActivity.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {

        homeActivity = mActivityTestrule.getActivity();
    }

    /**
    Viewwechsel zur CreateTextCoolNoteActivity
     */
    @Test
    public void onPlusButtonClickedTest() {
        onView(withId(plus_button)).perform(click());

        Activity createTextCoolNoteActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_createtextCoolNote,5000);
        assertNotNull(createTextCoolNoteActivity);
        createTextCoolNoteActivity.finish();
    }


    /**
     Viewwechsel zur FullTextCoolNoteActivity
     */
    @Test
    public void openFullCoolNoteTest(){

    }

    /**
     Viewwechsel zur FullTextFrzenNoteActivity
     */
    @Test
    public void openFullFrozenNoteTest(){

    }

    @After
    public void tearDown() throws Exception {

        homeActivity =null;
    }
}
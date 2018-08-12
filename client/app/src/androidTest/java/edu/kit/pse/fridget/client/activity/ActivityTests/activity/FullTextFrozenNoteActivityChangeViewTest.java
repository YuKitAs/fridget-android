package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static edu.kit.pse.fridget.client.R.id.back_button;
import static org.junit.Assert.assertNotNull;


/**
 * Diese Klasse testet Viewwechsel von der FullTextFrozenNoteActivity aus
 */
public class FullTextFrozenNoteActivityChangeViewTest {

    @Rule
    public ActivityTestRule<FullTextFrozenNoteActivity> mActivityTestrule =
            new ActivityTestRule<FullTextFrozenNoteActivity>(FullTextFrozenNoteActivity.class);

    private FullTextFrozenNoteActivity fullTextFrozenNoteActivity =null;

    Instrumentation.ActivityMonitor monitor_home =
            getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        fullTextFrozenNoteActivity = mActivityTestrule.getActivity();
    }

    /**
     Viewwechsel zur HomeActivity
     */
    @Test
    public void goBackTest() {
        onView(withId(back_button)).perform(click());

        Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_home,5000);
        assertNotNull(homeActivity);
        homeActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        fullTextFrozenNoteActivity =null;
    }
}


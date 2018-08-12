package edu.kit.pse.fridget.client.viewmodel;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

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


@SmallTest
public class HomeViewModelChangeViewTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestrule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

    private HomeActivity homeActivity =null;

    Instrumentation.ActivityMonitor monitor_createtextCoolNote = getInstrumentation().addMonitor(CreateTextCoolNoteActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor_fullTextCoolNote = getInstrumentation().addMonitor(FullTextCoolNoteActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor_fullTextFrozenNote = getInstrumentation().addMonitor(FullTextFrozenNoteActivity.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {

        homeActivity = mActivityTestrule.getActivity();
    }

    @Test
    public void onPlusButtonClickedTest() {
        onView(withId(plus_button)).perform(click());

        Activity createTextCoolNoteActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_createtextCoolNote,5000);
        assertNotNull(createTextCoolNoteActivity);
        createTextCoolNoteActivity.finish();
    }

    @Test
    public void onRefreshButtonClickedTest() {

    }

    @Test
    public void openFullCoolNoteTest(){

    }

    @Test
    public void openFullFrozenNoteTest(){

    }

}
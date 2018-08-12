package edu.kit.pse.fridget.client.activity.ActivityTests.activity;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;


/**
 * Diese Klasse testet Viewwechsel von der CreateFlatshareActivity aus
 */
public class CreateFlatshareActivityChangeViewTest {

    @Rule
    public ActivityTestRule<CreateFlatshareActivity> mActivityTestrule =
            new ActivityTestRule<CreateFlatshareActivity>(CreateFlatshareActivity.class);

    private CreateFlatshareActivity createFlatshareActivity =null;

    Instrumentation.ActivityMonitor monitor_home =
            getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        createFlatshareActivity = mActivityTestrule.getActivity();
    }

    /**
     Viewwechsel zur HomeActivity
     */
    @Test
    public void updateUITest() {

    }

    @After
    public void tearDown() throws Exception {
        createFlatshareActivity =null;
    }
}

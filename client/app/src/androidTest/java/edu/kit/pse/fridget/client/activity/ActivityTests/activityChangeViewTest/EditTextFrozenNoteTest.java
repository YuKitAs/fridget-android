package edu.kit.pse.fridget.client.activity.ActivityTests.activityChangeViewTest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditTextFrozenNoteTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void editTextFrozenNoteTest() {

        Context context = getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        ViewInteraction frozenNote = onView(withId(R.id.frozenNote1));
        frozenNote.perform(click());

        onView(withId(R.id.edit_button)).perform(click());

        ViewInteraction enterTitle = onView(withId(R.id.enterTitleEditFrozen));
        enterTitle.perform(click());
        enterTitle.perform(replaceText("testTitle"), closeSoftKeyboard());
        enterTitle.check(matches(withText("testTitle")));

        ViewInteraction enterContent = onView(withId(R.id.enterContentEditFrozen));
        enterContent.perform(click());
        enterContent.perform(replaceText("testContent"), closeSoftKeyboard());
        enterContent.check(matches(withText("testContent")));

        ViewInteraction saveButton = onView(withId(R.id.saveButton));
        saveButton.perform(click());

        ViewInteraction title = onView(withId(R.id.titleFrozen));
        title.check(matches(withText("testTitle")));

        ViewInteraction content = onView(withId(R.id.contentFrozen));
        content.check(matches(withText("testContent\n\n")));

        ViewInteraction backButton = onView(withId(R.id.back_button));
        backButton.perform(click());

    }
}

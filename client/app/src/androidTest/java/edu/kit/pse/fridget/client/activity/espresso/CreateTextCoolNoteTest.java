package edu.kit.pse.fridget.client.activity.espresso;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateTextCoolNoteTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void createTextCoolNoteTest() {

        Context context = getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String magnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        //click plus button
        ViewInteraction plusButton = onView(withId(R.id.plus_button));
        plusButton.perform(click());

        //check if view change was successful
        onView(withId(R.id.createCN)).check(matches(isDisplayed()));

        //enter title and check if the typed title is displayed
        ViewInteraction enterTitle = onView(withId(R.id.enterTitle));
        enterTitle.perform(click());
        enterTitle.perform(replaceText("testTitle"), closeSoftKeyboard());
        enterTitle.check(matches(withText("testTitle")));

        //enter content and check if the typed content is displayed
        ViewInteraction enterContent = onView(withId(R.id.enterContent));
        enterContent.perform(click());
        enterContent.perform(replaceText("testContent"), closeSoftKeyboard());
        enterContent.check(matches(withText("testContent")));

        //click post button
        ViewInteraction postButton = onView(withId(R.id.postButton));
        postButton.perform(click());

        //check if view change was successful
        onView(withId(R.id.fullCN)).check(matches(isDisplayed()));

        //check if correct title is displayed
        ViewInteraction title = onView(withId(R.id.title2));
        title.check(matches(withText("testTitle")));

        //check if correct content is displayed
        ViewInteraction content = onView(withId(R.id.content2));
        content.check(matches(withText("testContent\n\n")));

        //check if magnet has correct colour
        ViewInteraction magnet = onView(withId(R.id.magnet_CN));
        //magnet.check(matches(withBackgroundTint(Color.parseColor("#" + magnetColor))));

        //click on back button
        ViewInteraction backButton = onView(withId(R.id.back_button));
        backButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withBackgroundTint(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public boolean matchesSafely(ImageView magnet) {
                return color == ((ColorDrawable)magnet.getBackground()).getColor();
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
            }
        };
    }
}

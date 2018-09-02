package edu.kit.pse.fridget.client.activity.ActivityTests.activityChangeViewTest;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;

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

    private int randomIndex = 0;

    @Rule
    public ActivityTestRule<CreateTextCoolNoteActivity> mActivityTestRule = new ActivityTestRule<CreateTextCoolNoteActivity>(CreateTextCoolNoteActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, CreateTextCoolNoteActivity.class);
            Random generator = new Random();
            while (randomIndex == 0) {
                randomIndex = generator.nextInt(9);
                if (randomIndex == 0) {
                    break;
                }
            }
            intent.putExtra("position", randomIndex);
            return intent;
        }};

    @Test
    public void createTextCoolNoteTest() {

        Context context = getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String magnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

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
        //ViewInteraction magnet = onView(withId(R.id.magnet_CN));
        //magnet.check(matches(withBackgroundTint(Color.parseColor("#" + magnetColor))));

        //click on back button
        ViewInteraction backButton = onView(withId(R.id.back_button));
        backButton.perform(click());

        //check if cool note exists in home and when clicked display correctly
        if (randomIndex == 0) {
            ViewInteraction coolNote1 = onView(withId(R.id.coolNote1));
            coolNote1.check(matches(isDisplayed()));
            coolNote1.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 1) {
            ViewInteraction coolNote2 = onView(withId(R.id.coolNote2));
            coolNote2.check(matches(isDisplayed()));
            coolNote2.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 2) {
            ViewInteraction coolNote3 = onView(withId(R.id.coolNote3));
            coolNote3.check(matches(isDisplayed()));
            coolNote3.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 3) {
            ViewInteraction coolNote4 = onView(withId(R.id.coolNote4));
            coolNote4.check(matches(isDisplayed()));
            coolNote4.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 4) {
            ViewInteraction coolNote5 = onView(withId(R.id.coolNote5));
            coolNote5.check(matches(isDisplayed()));
            coolNote5.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 5) {
            ViewInteraction coolNote6 = onView(withId(R.id.coolNote6));
            coolNote6.check(matches(isDisplayed()));
            coolNote6.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 6) {
            ViewInteraction coolNote7 = onView(withId(R.id.coolNote7));
            coolNote7.check(matches(isDisplayed()));
            coolNote7.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 7) {
            ViewInteraction coolNote8 = onView(withId(R.id.coolNote8));
            coolNote8.check(matches(isDisplayed()));
            coolNote8.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
        if (randomIndex == 8) {
            ViewInteraction coolNote9 = onView(withId(R.id.coolNote9));
            coolNote9.check(matches(isDisplayed()));
            coolNote9.perform(click());
            onView(withId(R.id.fullCN)).check(matches(isDisplayed()));
            title.check(matches(withText("testTitle")));
            content.check(matches(withText("testContent\n\n")));
        }
    }

    /*
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
    }*/
}

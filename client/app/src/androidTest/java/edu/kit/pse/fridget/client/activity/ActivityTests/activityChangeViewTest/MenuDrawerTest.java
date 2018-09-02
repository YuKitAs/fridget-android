package edu.kit.pse.fridget.client.activity.ActivityTests.activityChangeViewTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuDrawerTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void showNavigationItemMemberList() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        // Start the screen of your activity.
        onView(withId(R.id.nav_view_home))
                .perform(NavigationViewActions.navigateTo(R.id.memlist));

        // Check that you Activity was opened.
        onView(withId(R.id.member_list)).check(matches(isDisplayed()));
    }

    @Test
    public void showNavigationItemAddMember() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        // Start the screen of your activity.
        onView(withId(R.id.nav_view_home))
                .perform(NavigationViewActions.navigateTo(R.id.add_member));

        // Check that you Activity was opened.
        onView(withId(R.id.fragment_create_access_code)).check(matches(isDisplayed()));
    }

    @Test
    public void showNavigationItemLeaveFlatshare() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        // Start the screen of your activity.
        onView(withId(R.id.nav_view_home))
                .perform(NavigationViewActions.navigateTo(R.id.leave_flatshare));

        // Check that you Activity was opened.
        onView(withId(R.id.fragment_leave_flatshare)).check(matches(isDisplayed()));
    }

}

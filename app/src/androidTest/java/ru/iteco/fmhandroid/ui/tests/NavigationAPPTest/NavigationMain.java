package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.tests.AuthorizationTests.SuccessfullLogin;
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationMain {

    DataHelper dataHelper = new DataHelper();
    SuccessfullLogin successfullLogin = new SuccessfullLogin();
    NavigationHelper navigationHelper = new NavigationHelper();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);

        try {
            AuthorizationHelper.auth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transitionFromMainToNewsTest() {
        NavigationHelper.transitionToNews();
        onView(withText("News"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void transitionFromMainToAboutTest() {
        NavigationHelper.transitionToAbout();
        NavigationHelper.textDisplayTitle(R.id.about_version_title_text_view);
        NavigationHelper.backButtonAbout();
        onView(withText("ALL NEWS"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void transitionFromMainToQuotesTest() {
        NavigationHelper.transitionToQuotes();
        NavigationHelper.textDisplayTitle(R.id.our_mission_title_text_view);
    }

    @Test
    public void goToNewsViaButtonALLNEWSTest() {
      NavigationHelper.clickButtonALLNEWSInMain();
        onView(withText("News"))
                .check(matches(isDisplayed()));
    }
    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        NavigationHelper.logOutOfYourAccount();
    }

}
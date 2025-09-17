package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

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
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationAbout {
    DataHelper dataHelper = new DataHelper();
    MainPage main = new MainPage();
    NavigationHelper navigationHelper = new NavigationHelper();
    AboutPage aboutPage = new AboutPage();
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
            navigationHelper.transitionToAbout();
        } catch (Exception E) {
        }
    }

    @Test
    public void backButtonAboutTest() { //кнопка назад из about
        aboutPage.versionTitleField.check(matches(isDisplayed()));
        NavigationHelper.textDisplayTitle(R.id.about_version_title_text_view);
        NavigationHelper.backButtonAbout();
        main.allNewsTextMatcher.check(matches(isDisplayed()));
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        NavigationHelper.logOutOfYourAccount();
    }
}

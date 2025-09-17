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
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationNews {
    DataHelper dataHelper = new DataHelper();
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
        } catch (Exception E) {
        }
        NavigationHelper.transitionToNews();
    }

    @Test
    public void transitionFromNewsToMainTest() {
        NavigationHelper.transitionToMain();
        NavigationHelper.textDisplayTitle(R.id.all_news_text_view);
    }

    @Test
    public void transitionFromNewsToAboutTest() { //кнопка About не активна
        NavigationHelper.transitionToAbout();
        onView(withText("About"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void transitionFromNewsToQuotesTest() { //переход в цитаты
        NavigationHelper.transitionToQuotes();
        onView(withId(R.id.our_mission_title_text_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void sortNewsByPressinAButtonTest() {
        NavigationHelper.sortNewsByPressinAButton();
        onView(
                withText("Whiting Wild Rice"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void goToNewsFilterSettingsTest() { //переход в фильтр новостей
        NavigationHelper.goToNewsFilterSettings();
        onView(withId(R.id.filter_news_title_text_view))
                .check(matches(isDisplayed()));
        onView(
                withId(R.id.cancel_button)
        ).perform(click());
    }

    @Test
    public void transitionToEditNewsTest() { //переход в редактирование новостей из новостей
        NavigationHelper.transitionToEditNews();
        onView(
                withText("Control panel"))
                .check(matches(isDisplayed()));

    }

    @After
    public void close() {
        NavigationHelper.logOutOfYourAccount();
        IdlingRegistry.getInstance().unregister(idlingResource);

    }
}

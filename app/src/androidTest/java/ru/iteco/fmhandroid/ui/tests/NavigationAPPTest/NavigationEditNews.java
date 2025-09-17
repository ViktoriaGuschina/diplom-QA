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
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.page.QuotesPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationEditNews {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    DataHelper dataHelper = new DataHelper();
    NavigationHelper navigationHelper = new NavigationHelper();
    CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
    QuotesPage quotesPage = new QuotesPage();
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
            navigationHelper.transitionToNews();
            navigationHelper.transitionToEditNews();
        } catch (Exception E) {
        }

    }

    @Test
    public void transitionToCreatingNewsTest() { //переход в создание новостей из новостей
        NavigationHelper.transitionToCreatingNews();
        onView(withText("Creating"))
                .check(matches(isDisplayed()));
        creatingNewsPage.cancelButton.perform(click());
        creatingNewsPage.okButtonMessage.perform(click());
    }

    @Test
    public void sortingNewsInNewsEditorTest() { //сортировка в редактировании новостей
        NavigationHelper.sortNewsByPressinAButton();
        onView(withText("Whiting Wild Rice"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void transitionFromEditNewsToQuotesTest() {
        NavigationHelper.transitionToQuotes();
        quotesPage.title.check(matches(isDisplayed()));
        onView(
                withId(R.id.cancel_button)
        ).perform(click());
    }

    @Test
    public void enterFiltersInNewsEditorTest() { //заход в фильтр редактора новостей
        NavigationHelper.goToNewsFilterSettings();
        NavigationHelper.textDisplayTitle(R.id.filter_news_title_text_view);
        onView(
                withId(R.id.cancel_button)
        ).perform(click());
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);

        NavigationHelper.logOutOfYourAccount();
    }
}

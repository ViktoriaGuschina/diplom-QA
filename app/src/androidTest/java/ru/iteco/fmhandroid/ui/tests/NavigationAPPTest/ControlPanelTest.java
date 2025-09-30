package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@Epic("Контрольная панель тесты")
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelTest {
    CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
    OurMissionPage quotesPage = new OurMissionPage();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
        try {
            AuthorizationSteps.auth();
            MainSteps.transitionToNews();
            NewsSteps.transitionToControlPanel();
        } catch (Exception E) {
        }
    }

    @Test
    @Story("Переход в Creating news ")
    public void transitionToCreatingNewsTest() {
        ControlPanelSteps.transitionToCreatingNews();
        CreatingNewsSteps.cheсkingTitleCreatingNews();
        creatingNewsPage.cancelButton.perform(click());
        creatingNewsPage.okButtonMessage.perform(click());
    }

    @Test
    @Story("Сортировка новостей в Control panel и вывод в консоль перв")
    public void sortingNewsInNewsEditorTest() {
        NewsSteps.sortNewsByPressinAButton();
        ControlPanelSteps.selectFirstNewsItemInListControlPanel();
    }

    @Test
    @Story("Переход в Our Mission")
    public void transitionFromControlPageToOurMissionTest() {
        MainSteps.transitionToOurMission();
        quotesPage.title.check(matches(isDisplayed()));
    }

    @Test
    @Story("Переход в фильтр новостей в Control Panel")
    public void enterFiltersInNewsEditorTest() {
        FilterNewsSteps.goToNewsFilterSettings();
        FilterNewsSteps.chekingTitleFilter();
        FilterNewsSteps.clickBackButton();
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        try {
            AuthorizationSteps.logOut();
        } catch (Exception e) {
        }
    }
}

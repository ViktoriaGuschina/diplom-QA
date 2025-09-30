package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.action.ViewActions.click;
import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.Rand.randomCategory;

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
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@Epic("Создание и удаление новости")
@RunWith(AllureAndroidJUnit4.class)
public class DeleteNewsTest {
    CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
    CreatingNewsSteps creatingNewsSteps = new CreatingNewsSteps();
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

            MainSteps.clickAllNews();

            NewsSteps.transitionToControlPanel();

            ControlPanelSteps.transitionToCreatingNews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Story("Создание и удаление новости")
    public void creatingAndDeletingNewsItemTest() {

        String category = randomCategory();
        CreatingNewsSteps.fillInNewsCategory(category);

        String title = "Новость дня";
        String description = "Описание новости тест";
        creatingNewsPage.categoryField.perform(click());
        CreatingNewsSteps.coosingTodaysDate();
        CreatingNewsSteps.coosingTodaysTime();
        creatingNewsSteps.createNews(title, description);
        CreatingNewsSteps.clickSaveButton();
        ControlPanelSteps.clickDeleteNews(title);
        ControlPanelSteps.checkThatControlPanelContentIsFull();
        NewsSteps.checkIfNoNewsWithTitle(title);

        allureScreenshot("creating And Deleting News Item Test", 100, 1.0f);
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

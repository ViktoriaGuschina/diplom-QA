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
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@Epic("Создание новости")
@RunWith(AllureAndroidJUnit4.class)
public class CreatingNewsTest {
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
        } catch (Exception E) {
        }
    }

    @Test
    @Story("Наличие всех элементов Creatin News")
    public void shouldOpenCreateNewsTest() {
        CreatingNewsSteps.checkThatCreateNewsPageContentIsFull();
        NavigationHelper.clickCancelButton();
        CreatingNewsSteps.clickOKButton();

        allureScreenshot("should Open Create News test", 100, 1.0f);
    }

    @Test
    @Story("Cоздание новости")
    public void shouldCreateNewsValidTest() {
        String category = randomCategory();
        CreatingNewsSteps.fillInNewsCategory(category);

        String title = "Новость дня";
        String description = "Описание новости тест";
        creatingNewsPage.categoryField.perform(click());
        CreatingNewsSteps.coosingTodaysDate();
        CreatingNewsSteps.coosingTodaysTime();
        creatingNewsSteps.createNews(title, description);
        CreatingNewsSteps.clickSaveButton();
        NavigationHelper.checkIfNewsWithTitle(title);

        allureScreenshot("should Create News Valid Test", 100, 1.0f);
    }

    @Test
    @Story("Отмена создания новости")
    public void shouldCancelCreateNewsTest() {
        NavigationHelper.clickCancelButton();
        CreatingNewsSteps.clickOKButton();
        ControlPanelSteps.checkThatControlPanelContentIsFull();

        allureScreenshot("should Cancel Create News Test", 100, 1.0f);
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

package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;
import static ru.iteco.fmhandroid.ui.steps.NewsSteps.sortNewsByPressinAButton;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
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
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.OurMissionSteps;

@Epic("Тесты навигации новостей")
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

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
        } catch (Exception E) {
        }
    }

    @Test
    @Story("Переход из News в Main")
    public void transitionFromNewsToMainTest() {
        MainSteps.transitionToMain();
        MainSteps.checkingAllNews();

        allureScreenshot("transition From News To Main Test", 100, 1.0f);
    }

    @Test
    @Story("Переход из News в About")
    public void transitionFromNewsToAboutTest() {
        MainSteps.transitionToAbout();
        AboutSteps.checkingText();
        allureScreenshot("transition From News To About Test", 100, 1.0f);
    }

    @Test
    @Story("Переход из News из Our Mission")
    public void transitionFromNewsToQuotesTest() {
        MainSteps.transitionToOurMission();
        OurMissionSteps.checkingTitleOurMission();

        allureScreenshot("transition From News To Quotes Test", 100, 1.0f);
    }

    @Test
    @Story("Сортировка новостей")
    public void sortNewsByPressingButtonTest() {
        sortNewsByPressinAButton();
        DataHelper.searchInSheet(R.id.news_list_recycler_view);

        allureScreenshot("sort News By Pressing Button Test", 100, 1.0f);
    }

    @Test
    @Story("Переход в настройку фильтра новостей")
    public void goToNewsFilterSettingsTest() {
        FilterNewsSteps.goToNewsFilterSettings();
        FilterNewsSteps.chekingTitleFilter();
        FilterNewsSteps.clickBackButton();

        allureScreenshot("go To News Filter Settings Test", 100, 1.0f);
    }

    @Test
    @Story("Переход в редактирование новосте")
    public void transitionToControlPanelTest() {
        NewsSteps.transitionToControlPanel();
        ControlPanelSteps.checkingTitleControlPanel();

        allureScreenshot("transition To Control Panel Test", 100, 1.0f);
    }

    @Test
    @Story("Выбор третьей новости в списке и вывод текста")
    public void selectThirdNewsAndLogTextTest() {
        final int NEWS_ITEM_POSITION = 2;
        final String LOG_TAG = "NewsTest";
        int NEWS_RECYCLER_VIEW_ID = R.id.news_list_recycler_view;

        Espresso.onView(withId(NEWS_RECYCLER_VIEW_ID))
                .perform(RecyclerViewActions.actionOnItemAtPosition(NEWS_ITEM_POSITION, new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "Клик по третьему элементу и лог текста новости";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();

                        TextView newsTextView = view.findViewById(R.id.news_item_description_text_view);
                        if (newsTextView != null) {
                            String newsText = newsTextView.getText().toString();
                            Log.d(LOG_TAG, "Текст новости: " + newsText);
                        } else {
                            Log.e(LOG_TAG, "TextView с новостью не найден в элементе");
                        }
                    }
                }));
        allureScreenshot("select Third News And Log Text test", 100, 1.0f);
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


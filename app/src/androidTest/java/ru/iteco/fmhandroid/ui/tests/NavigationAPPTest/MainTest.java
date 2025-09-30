package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;

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
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@Epic("Тесты главного раздела")
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Story("Переход в News из Main")
    public void transitionFromMainToNewsTest() {
        MainSteps.transitionToNews();
        NewsSteps.checkingTitle();

        allureScreenshot("transition From Main To News Test", 100, 1.0f);
    }

    @Test
    @Story("Переход в About из Main")
    public void transitionFromMainToAboutTest() {
        MainSteps.transitionToAbout();
        NavigationHelper.textDisplayTitle(R.id.about_version_title_text_view);
        AboutSteps.backButtonAbout();
        MainSteps.checkingAllNews();

        allureScreenshot("transition From Main To About Test", 100, 1.0f);
    }

    @Test
    @Story("Переход в Our Mission из Main")
    public void transitionFromMainToQuotesTest() {
        MainSteps.transitionToOurMission();
        NavigationHelper.textDisplayTitle(R.id.our_mission_title_text_view);

        allureScreenshot("transition From Main To Our Mission Test", 100, 1.0f);
    }

    @Test
    @Story("Нажать на кнопку ALL NEWS в Main")
    public void goToNewsViaButtonALLNEWSTest() {
        NewsSteps.clickButtonALLNEWSInMain();
        NewsSteps.checkingTitle();

        allureScreenshot("go To News Via Button ALL NEWS Test", 100, 1.0f);
    }

    @Test
    @Story("Клик по первой новости и получение текста из него")
    public void clickSecondNewsItemAndLogTextTest() {
        final int position = 1;
        MainSteps.waiterNewsList();
        Espresso.onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isAssignableFrom(View.class);
                    }

                    @Override
                    public String getDescription() {
                        return "Click and log text of news item at position " + position;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();

                        TextView newsTextView = view.findViewById(R.id.news_item_description_text_view);
                        if (newsTextView != null) {
                            String text = newsTextView.getText().toString();
                            Log.d("NewsTest", "News item text: " + text);
                        } else {
                            Log.e("NewsTest", "TextView with news text not found");
                        }
                    }
                }));
        allureScreenshot("click Second News Item And Log Text test", 100, 1.0f);
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
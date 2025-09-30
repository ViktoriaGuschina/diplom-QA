package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;

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
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.OurMissionSteps;

@Epic("Тесты раздела цитат")
@RunWith(AllureAndroidJUnit4.class)
public class OurMissionTest {

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
            MainSteps.transitionToOurMission();
        } catch (Exception E) {
        }
    }

    @Test
    @Story("Наличие контента и скрол вниз")
    public void presenceOfContentAndScrollQuotesTest() {
        OurMissionSteps.checkThatOurMissionBlockContentIsFull();
        OurMissionSteps.scroll();

        allureScreenshot("presence Of Content And Scroll Our Mission Test", 100, 1.0f);
    }

    @Test
    @Story("Переход из Our Mission в Main")
    public void transitionFromOurMissionToMainTest() {
        MainSteps.transitionToMain();
        MainSteps.checkingAllNews();

        allureScreenshot("transition From Our Mission To Main Test", 100, 1.0f);
    }

    @Test
    @Story("Переход из Our Mission в News")
    public void transitionFromOurMissionToNewsTest() {
        MainSteps.transitionToNews();
        NewsSteps.checkingTitle();

        allureScreenshot("transition From Our Mission To News Test", 100, 1.0f);
    }

    @Test
    @Story("Свернуть/развернуть цитату")
    public void shouldBeDisplayOurMissionTest() {
        String quoteTestText = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер";
        OurMissionSteps.checkOurMission(0);
        OurMissionSteps.descriptionIsDisplay(quoteTestText);
        OurMissionSteps.checkOurMission(0);

        allureScreenshot("should Be Display Our Mission Test", 100, 1.0f);
    }

    @Test
    @Story("Переход из Our Mission в About")
    public void transitionFromOurMissionToAboutTest() {
        MainSteps.transitionToAbout();
        AboutSteps.checkingText();
        AboutSteps.backButtonAbout();

        allureScreenshot("transition From Our Mission To About Test", 100, 1.0f);
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

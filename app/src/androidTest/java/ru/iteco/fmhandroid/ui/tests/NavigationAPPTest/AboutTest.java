package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@Epic("Тесты о приложении")
@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {
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
            AuthorizationSteps.auth();
            MainSteps.transitionToAbout();
        } catch (Exception E) {
        }
    }

    @Test
    @Story("В разделе About нажать кнопку назад")
    public void backButtonAboutTest() {
        aboutPage.versionText.check(matches(isDisplayed()));
        NavigationHelper.textDisplayTitle(R.id.about_version_title_text_view);
        AboutSteps.backButtonAbout();
        MainPage.allNewsButton.check(matches(isDisplayed()));

        allureScreenshot("back Button About Test", 100, 1.0f);
    }

    @Test
    @Story("В разделе About налиие всех компонентов")
    public void shouldBeFullContentAboutPageTest() {
        AboutSteps.fullPresenceOfAllComponentsOfAboutPage();
        AboutSteps.backButtonAbout();
        allureScreenshot("should Be Full Content About Page Test", 100, 1.0f);
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

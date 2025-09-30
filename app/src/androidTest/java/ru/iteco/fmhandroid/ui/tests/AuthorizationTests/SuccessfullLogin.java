package ru.iteco.fmhandroid.ui.tests.AuthorizationTests;

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
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

@Epic("Авторизация")
@RunWith(AllureAndroidJUnit4.class)

public class SuccessfullLogin {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    public static ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    @Story("Отображение всех элементов авторизации")
    public void shouldBeFullContentInAboutBlock() {
        AuthorizationSteps.fullPresenceOfAllComponentsOfAuthorizationForm();

        allureScreenshot("should Be Full Content In About Block", 100, 1.0f);
    }

    @Test
    @Story("Успешный вход в приложение с валидными данными")
    public void successAuthTest() {
        NavigationHelper.textDisplayTitle(R.id.auth);
        AuthorizationSteps.auth();
        AuthorizationSteps.logOut();

        allureScreenshot("success Auth Test", 100, 1.0f);
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}

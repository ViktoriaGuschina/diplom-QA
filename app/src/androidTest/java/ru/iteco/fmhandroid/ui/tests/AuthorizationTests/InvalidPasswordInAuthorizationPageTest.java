package ru.iteco.fmhandroid.ui.tests.AuthorizationTests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;

import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataCapitalLettersENInPassword;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataLeaveFieldEmpty;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataNumbers;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataOneDigit;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataRULetters;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataSpaces;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.invalidDataSpecialCharacters;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.validLogin;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

@Epic("Невалидные тесты пароля")
@RunWith(AllureAndroidJUnit4.class)
public class InvalidPasswordInAuthorizationPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - одной цифрой")
    public void enteringOneNumberInPasswordFieldTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataOneDigit()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("enteringOneNumberInPasswordFieldTest", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - пробелами")
    public void fillInPasswordFieldWithSpacesTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataSpaces()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("fill In Password Field With Spaces Test", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - заглавными буквами")
    public void enteringCapitalLettersENInPasswordFieldTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataCapitalLettersENInPassword()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("entering Capital Letters EN In Password Field Test", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - русскими буквами")
    public void fillInPasswordFieldWithRULettersTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(replaceText(invalidDataRULetters()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("fill In Password Field With RU Letters Test", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - заглавными буквами")
    public void fillInPasswordFieldWithSpecialCharactersTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataSpecialCharacters()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("fill In Password Field With Special Character Test", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль пустой строкой")
    public void leavePasswordFieldEmptyTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataLeaveFieldEmpty()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        allureScreenshot("leave Password Field Empty Test", 100, 1.0f);
    }

    @Test
    @Story("Заполнение поля пароль не валидными данными - разными цифрами")
    public void enteringNumbersInPasswordFieldTest() {
        AtomicReference<String> emptyLoginOrPassword = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            emptyLoginOrPassword.set(activity.getApplicationContext().getString(R.string.empty_login_or_password));
        });
        authorizationPage.loginField.perform(typeText(validLogin()));
        authorizationPage.passwordField.perform(typeText(invalidDataNumbers()));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(emptyLoginOrPassword.get()));

        toast.exists();
        allureScreenshot("entering Numbers In Password Field Test", 100, 1.0f);
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}


package ru.iteco.fmhandroid.ui.tests.AuthorizationTests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;

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

import java.util.concurrent.atomic.AtomicReference;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;

public class InvalidPasswordInAuthorizationPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    DataHelper dataHelper = new DataHelper();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
        try {
            NavigationHelper.logOutOfYourAccount();
        } catch (Exception E) {
        }
    }

    @Test
    public void enteringOneNumberInPasswordFieldTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText("p2"));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void fillInPasswordFieldWithSpacesTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText("       "));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void enteringCapitalLettersENInPasswordFieldTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText("PASSWORD2"));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void fillInPasswordFieldWithRULettersTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(replaceText("пароль"));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void fillInPasswordFieldWithSpecialCharactersTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText("@#$%^&*()"));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void leavePasswordFieldEmptyTest() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText(""));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
    }

    @Test
    public void enteringNumbersInPasswordFieldTest() {
        AtomicReference<String> emptyLoginOrPassword = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            emptyLoginOrPassword.set(activity.getApplicationContext().getString(R.string.empty_login_or_password));
        });

        authorizationPage.loginField.perform(typeText("login2"));
        authorizationPage.passwordField.perform(typeText("0987654321"));
        authorizationPage.loginButton.perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(emptyLoginOrPassword.get()));

        toast.exists();
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


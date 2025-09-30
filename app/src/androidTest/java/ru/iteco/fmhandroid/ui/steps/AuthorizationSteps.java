package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helpers.WaitForView.waitView;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

public class AuthorizationSteps {
    static DataHelper dataHelper = new DataHelper();
    static AuthorizationPage authorizationPage = new AuthorizationPage();
    static MainPage mainPage = new MainPage();

    public static void fullPresenceOfAllComponentsOfAuthorizationForm() {
        Allure.step("Полное присутствие всех компонентов формы авторизации");
        authorizationPage.title.check(matches(isDisplayed()));
        authorizationPage.loginField.check(matches(isDisplayed()));
        authorizationPage.passwordField.check(matches(isDisplayed()));
        authorizationPage.loginButton.check(matches(isDisplayed()));
    }

    public static void auth() {
        Allure.step("Авторизация с валидными данными");
        authorizationPage.loginField.perform(typeText(dataHelper.getValidUser().getLogin()), closeSoftKeyboard());
        authorizationPage.passwordField.perform(typeText(dataHelper.getValidUser().getPassword()), closeSoftKeyboard());
        authorizationPage.loginButton.perform(click());
    }

    public static void logOut() {
        Allure.step("Выход из аккаунта");
        onView(isRoot()).perform(waitView(withId(R.id.authorization_image_button), 3000));
        mainPage.authButton
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
        mainPage.logOutButton
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
    }
}

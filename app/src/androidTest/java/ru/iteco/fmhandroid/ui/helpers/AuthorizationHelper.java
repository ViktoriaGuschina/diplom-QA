package ru.iteco.fmhandroid.ui.helpers;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

//import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;

public class AuthorizationHelper {

    static AuthorizationPage authorizationPage = new AuthorizationPage();
    public static void checkThatAuthorizationBlockContentIsFull() {
        // Allure.step("Наличие всех элементов формы авторизации");
        authorizationPage.title.check(matches(isDisplayed()));
        authorizationPage.loginField.check(matches(isDisplayed()));
        authorizationPage.passwordField.check(matches(isDisplayed()));
        authorizationPage.loginButton.check(matches(isDisplayed()));
    }

    public static void auth() {
        //  Allure.step("Авторизация в приложении с валидными данными");
        DataHelper help = new DataHelper();
        authorizationPage.loginField.perform(typeText(help.getValidUser().getLogin()), closeSoftKeyboard());
        authorizationPage.passwordField.perform(typeText(help.getValidUser().getPassword()), closeSoftKeyboard());
        authorizationPage.loginButton.perform(click());
    }
}

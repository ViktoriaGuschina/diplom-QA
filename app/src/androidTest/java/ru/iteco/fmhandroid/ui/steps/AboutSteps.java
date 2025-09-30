package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.page.AboutPage;

public class AboutSteps {
    static AboutPage aboutPage = new AboutPage();
    public static void backButtonAbout() {
        Allure.step("кнопка назад из about");
        aboutPage.backButton
                .perform(click());
    }

    public static void fullPresenceOfAllComponentsOfAboutPage() {
        Allure.step("Присутствие всех компонентов About");
        aboutPage.termsOfUseText.check(matches(isDisplayed()));
        aboutPage.infoCompanyLabel.check(matches(isDisplayed()));
        aboutPage.privacyPolicyLink.check(matches(isDisplayed()));
        aboutPage.linkTermsOfUse.check(matches(isDisplayed()));
        aboutPage.trademark.check(matches(isDisplayed()));
        aboutPage.backButton.check(matches(isDisplayed()));
        aboutPage.versionText.check(matches(isDisplayed()));
        aboutPage.applicationVersionNumber.check(matches(isDisplayed()));
        aboutPage.privacyPolicyText.check(matches(isDisplayed()));
    }
    public static void checkingText() {
        Allure.step("Наличие текста Privacy Policy");
        NavigationHelper.textDisplayTitle(R.id.about_privacy_policy_value_text_view);
    }

}

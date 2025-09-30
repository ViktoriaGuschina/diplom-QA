package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.helpers.WaitForView.waitView;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

public class MainSteps {
    public static void transitionToNews() {
        Allure.step("переход в новости через меню");
        onView(isRoot()).perform(waitView(withId(R.id.main_menu_image_button), 5000));
        MainPage.menuButton
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
        onView(withText("News"))
                .perform(click());
    }

    public static void transitionToAbout() {
        Allure.step("Переход в о приложении через меню");
        onView(isRoot()).perform(waitView(withId(R.id.main_menu_image_button), 3000));
        MainPage.menuButton
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("About")).perform(click());
    }

    public static void transitionToOurMission() {
        Allure.step("переход в цитаты");
        onView(isRoot()).perform(waitView(withId(R.id.our_mission_image_button), 6000));
        OurMissionPage.buttonOurMission()
                .perform(click());
    }

    public static void transitionToMain() {
        Allure.step("переход в основной раздел");
        onView(isRoot()).perform(waitView(withId(R.id.main_menu_image_button), 3000));
        MainPage.menuButton
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("Main")).perform(click());
    }
    public static void checkingAllNews() {
        Allure.step("Наличие ALL NEWS");
    onView(withText("ALL NEWS"))
            .check(matches(isDisplayed()));
    }
    public static void waiterNewsList() {
        Allure.step("Ожидание прогрузки листа новостей");
        onView(isRoot()).perform(waitView(withId(R.id.news_list_recycler_view), 3000));
    }
    public static void clickAllNews() {
        Allure.step("Переход в новости через ALL NEWS");
        onView(isRoot()).perform(waitView(withId(R.id.all_news_text_view), 3000));
        MainPage.allNewsButton.perform(click());
    }
}

package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.helpers.WaitForView.waitView;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

public class NewsSteps {
    static NewsPage newsPage = new NewsPage();

    public static void clickButtonALLNEWSInMain() {
        Allure.step("Клик на кнопку все новости");
        MainPage.allNewsButton.perform(click());
    }

    public static void sortNewsByPressinAButton() {
        Allure.step("сортировка в новостях");
        onView(isRoot()).perform(waitView(withId(R.id.sort_news_material_button), 3000));
        NewsPage.sortButton
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void transitionToControlPanel() {
        Allure.step("Переход в панель управления со страницы Новостей");
        onView(isRoot()).perform(waitView(withId(R.id.edit_news_material_button), 3000));
        newsPage.controlPanelButton.perform(click());
    }

    public static void checkIfNoNewsWithTitle(String titleText) {
        Allure.step("Наличие отсутствия новости с указанным заголовком");
        onView(allOf(withText(titleText), isDisplayed())).check(doesNotExist());
    }

    public void clickOneNewsItem(int index) {
        Allure.step("Свернуть/развернуть новость");
        NewsPage.childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }

    public static void checkingTitle() {
        Allure.step("Наличие тайтла News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
    }
}

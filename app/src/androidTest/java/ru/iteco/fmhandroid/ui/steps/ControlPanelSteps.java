package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

public class ControlPanelSteps {
    static ControlPanelPage controlPanelPage = new ControlPanelPage();
    public static void checkThatControlPanelContentIsFull() {
        Allure.step("Проверка панели управления на полный контент");
        controlPanelPage.trademark.check(matches(isDisplayed()));
        controlPanelPage.sortNewsButton.check(matches(isDisplayed()));
        controlPanelPage.filterButton.check(matches(isDisplayed()));
        controlPanelPage.addNewsButton.check(matches(isDisplayed()));
    }
    public static void transitionToCreatingNews() {
        Allure.step("Переход в создание новостей из новостей");
        NewsPage.addNewsImageMatcher
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void clickDeleteNews(String newsTitle) {
        Allure.step("Удалить новость с указанным заголовком");
        ControlPanelPage.deleteNewsButton(newsTitle).perform(click());
        CreatingNewsSteps.clickOKButton();
    }
    public static void checkingTitleControlPanel() {
        Allure.step("Наличие тайтла контрол панель");
        onView(
                withText("Control panel"))
                .check(matches(isDisplayed()));
    }

    public static void selectFirstNewsItemInListControlPanel() {
        Allure.step("Выбор первой новости в списке");
        DataHelper.searchInSheet(R.id.news_list_recycler_view);
    }
}

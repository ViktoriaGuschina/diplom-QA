package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.helpers.WaitForView.waitView;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

public class FilterNewsSteps {
    static NewsPage newsPage = new NewsPage();
    static FilterNewsPage filterNewsPage = new FilterNewsPage();

    public static void goToNewsFilterSettings() {
        Allure.step("Переход в фильтр новостей");
        newsPage.filterButton
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Категория");
        filterNewsPage.categoryField.perform(replaceText(text));
    }

    public static void clickFilterButton() {
        Allure.step("Клик по кнопке фильтр");
        onView(isRoot()).perform(waitView(withId(R.id.filter_button), 3000));
        filterNewsPage.filterButton.check(matches(isDisplayed()));
        filterNewsPage.filterButton.check(matches(isEnabled()));
        filterNewsPage.filterButton.perform(click());
    }

    public static void chekingTitleFilter() {
        Allure.step("Наличие тайтла фильтра");
        NavigationHelper.textDisplayTitle(R.id.filter_news_title_text_view);
    }
    public static void clickBackButton() {
        Allure.step("Выход из формы фильтра новостей");
        filterNewsPage.cancelButton
                .check(matches(isDisplayed()))
                .perform(click());
    }
}

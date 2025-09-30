package ru.iteco.fmhandroid.ui.helpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.helpers.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.page.CreatingNewsPage.cancelButton;

import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

public class NavigationHelper {
static MainPage mainPage = new MainPage();
static OurMissionPage quotesPage = new OurMissionPage();
static AboutPage aboutPage = new AboutPage();
static NewsPage newsPage = new NewsPage();
static CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
static ControlPanelPage controlPanelPage = new ControlPanelPage();



    public static void textDisplayTitle(Integer id) {
        // Allure.step("Видимость текста");
        onView(withId(id))
                .check(matches(isDisplayed()));
    }
    public static void clickCancelButton() {
      //  Allure.step("Нажатие кнопки отмена");
        cancelButton.perform(click());
    }

    public static void checkIfNewsWithTitle(String titleText) {
       // Allure.step("Наличие новости с указанным заголовком");
        onView(withIndex(allOf(withText(titleText), isDisplayed()), 0)).check(matches(isDisplayed()));
    }
}

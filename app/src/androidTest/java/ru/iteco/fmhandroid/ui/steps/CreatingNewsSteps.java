package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.page.CreatingNewsPage.cancelButton;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;

public class CreatingNewsSteps {
    static CreatingNewsPage creatingNewsPage = new CreatingNewsPage();

    public static void clickOKButton() {
        Allure.step("Нажатие кнопки ОК в сообщении  в разделе создания новости");
        creatingNewsPage.okButtonMessage.perform(click());
    }

    public static void clickSaveButton() {
        Allure.step("Нажатие кнопки Сохранить");
        creatingNewsPage.saveButton.perform(click());
    }

    public void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Категория");
        creatingNewsPage.categoryField.perform(replaceText(text));
    }

    public void fillInNewsTitleField(String text) {
        Allure.step("Ввод данных в поле Заголовок");
        creatingNewsPage.titleField.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
        Allure.step("Ввод данных в поле Дата публикации");
        creatingNewsPage.publicationDateField.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
        Allure.step("Ввод данных в поле Время");
        creatingNewsPage.timeField.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
        Allure.step("Ввод данных в поле Описание");
        creatingNewsPage.descriptionField.perform(replaceText(text));
    }

    public void createNews(String title, String description) {
        Allure.step("Ввод данных для создания новости");
        fillInNewsTitleField(title);
        fillInNewsDescriptionField(description);
    }

    public static void checkThatCreateNewsPageContentIsFull() {
        Allure.step("Наличие полного контента в создании новости");
        creatingNewsPage.title.check(matches(isDisplayed()));
        creatingNewsPage.categoryField.check(matches(isDisplayed()));
        creatingNewsPage.titleField.check(matches(isDisplayed()));
        creatingNewsPage.descriptionField.check(matches(isDisplayed()));
        creatingNewsPage.publicationDateField.check(matches(isDisplayed()));
        creatingNewsPage.timeField.check(matches(isDisplayed()));
        creatingNewsPage.switcher.check(matches(isDisplayed()));
        creatingNewsPage.saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    public static void cheсkingTitleCreatingNews() {
        Allure.step("Наличие тайтла создания новости");
        onView(withText("Creating"))
                .check(matches(isDisplayed()));
    }
    public static void fillInNewsCategory(String text) {
        Allure.step("Ввод данных в поле Категория");
        creatingNewsPage.categoryField.perform(replaceText(text));
    }
    public static void coosingTodaysDate() {
        Allure.step("Выбор сегодняшней даты");
        creatingNewsPage. publicationDateField.perform(click());
        creatingNewsPage.okButtonMessage.perform(click());
    }
    public static void coosingTodaysTime() {
        Allure.step("Выбор ныняшнего времени");
        creatingNewsPage. timeField.perform(click());
        creatingNewsPage.okButtonMessage.perform(click());
    }
}

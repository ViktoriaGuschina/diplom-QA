package ru.iteco.fmhandroid.ui.helpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.helpers.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.page.CreatingNewsPage.cancelButton;

import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.QuotesPage;

public class NavigationHelper {
static MainPage mainPage = new MainPage();
static QuotesPage quotesPage = new QuotesPage();
static AboutPage aboutPage = new AboutPage();
static NewsPage newsPage = new NewsPage();
static CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
static ControlPanelPage controlPanelPage = new ControlPanelPage();

    public static void logOutOfYourAccount() {
        MainPage main = new MainPage();
        main.profileButton.perform(click());
        main.logOutButton.perform(click());
    }

    public static void textDisplayTitle(Integer id) {
        onView(withId(id))
                .check(matches(isDisplayed()));
    }

    public static void clickButtonALLNEWSInMain() {
        mainPage.allNewsTextMatcher.perform(click());
    }

    public static void transitionToNews() {
        mainPage.menuButton
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("News"))
                .perform(click());
    }

    public static void transitionToAbout() {
        mainPage.menuButton
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("About")).perform(click());
    }

    public static void transitionToQuotes() {
        quotesPage.title
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void transitionToMain() {
        mainPage.menuButton
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("Main")).perform(click());
    }

    public static void sortNewsByPressinAButton() {
        newsPage.sort
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void goToNewsFilterSettings() { //переход в фильтр новостей
        newsPage.filter
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void transitionToEditNews() { //переход в редактирование новостей из новостей
        newsPage.controlPanelButton
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void transitionToCreatingNews() { //переход в создание новостей из новостей
        newsPage.addNewsImageMatcher
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openControlPanelPage() {
        //Allure.step("Переход в панель управления со страницы Новостей");
       newsPage.controlPanelButton.perform(click());
    }
    public void checkIfNoNewsWithTitle(String titleText) {
      //  Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(allOf(withText(titleText), isDisplayed())).check(doesNotExist());
    }
    public void checkThatControlPanelContentIsFull() {
       // Allure.step("Проверка панели управления на полный контент");
        controlPanelPage.logo.check(matches(isDisplayed()));
        controlPanelPage.sortButton.check(matches(isDisplayed()));
        controlPanelPage.filterButton.check(matches(isDisplayed()));
        controlPanelPage.addNewsButton.check(matches(isDisplayed()));
    }
    public void clickCancelButton() {
      //  Allure.step("Нажатие кнопки \"Отмена\"");
        cancelButton.perform(click());
    }

    public static void backButtonAbout() {//кнопка назад из about
        aboutPage.backButton
                .perform(click());
    }
    public void clickOKButton() {
        //Allure.step("Нажатие кнопки ОК в сообщении");
        creatingNewsPage.okButtonMessage.perform(click());
    }
    public void clickDeleteNews(String newsTitle) {
        //Allure.step("Удалить новость с указанным заголовком");
        controlPanelPage.deleteNewsButton(newsTitle).perform(click());
        clickOKButton();
    }

    public static void buttonToEditNewsAlreadyPublished(String newsTitle) { //кнопка редактирования уже опубликованной новости
        controlPanelPage.editNewsButton(newsTitle).perform(click());
    }
    public static void clickSaveButton() {
        //Allure.step("Нажатие кнопки \"Сохранить\"");
        creatingNewsPage.saveButton.perform(click());
    }

    public void fillInNewsCategoryField(String text) {
//        Allure.step("Ввод данных в поле \"Категория\"");
        creatingNewsPage.categoryText.perform(replaceText(text));
    }
    public void checkIfNewsWithTitle(String titleText) {
       // Allure.step("Проверка наличия новости с указанным заголовком");
        onView(withIndex(allOf(withText(titleText), isDisplayed()), 0)).check(matches(isDisplayed()));
    }
    public void fillInNewsTitleField(String text) {
//        Allure.step("Ввод данных в поле \"Заголовок\"");
        creatingNewsPage.titleText.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
//        Allure.step("Ввод данных в поле \"Дата публикации\"");
        creatingNewsPage.publicationDate.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
//        Allure.step("Ввод данных в поле \"Время\"");
        creatingNewsPage.time.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
//        Allure.step("Ввод данных в поле \"Описание\"");
        creatingNewsPage.descriptionText.perform(replaceText(text));
    }
    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
//        Allure.step("Ввод данных для создания новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
        fillInNewsDescriptionField(description);
    }
    public static void scroll() {
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(0));
    }
    public static void checkThatCreateNewsPageContentIsFull() {
        // Allure.step("Проверка окна Создания новости на полный контент");
        creatingNewsPage.titlePage.check(matches(isDisplayed()));
        creatingNewsPage.categoryText.check(matches(isDisplayed()));
        creatingNewsPage.titleText.check(matches(isDisplayed()));
        creatingNewsPage.descriptionText.check(matches(isDisplayed()));
        creatingNewsPage.publicationDate.check(matches(isDisplayed()));
        creatingNewsPage.time.check(matches(isDisplayed()));
        creatingNewsPage.switcher.check(matches(isDisplayed()));
        creatingNewsPage.saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }
}

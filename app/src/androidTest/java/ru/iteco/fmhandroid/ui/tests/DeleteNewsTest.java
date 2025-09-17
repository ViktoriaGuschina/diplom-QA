package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.helpers.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.getCurrentTime;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;

public class DeleteNewsTest {

    NavigationHelper navigationHelper = new NavigationHelper();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);

        try {
            AuthorizationHelper.auth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
//    @Feature(value = "Тесты раздела Новости")
//    @Story("Удаление новости")
    public void shouldDeleteNews() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "Новость delete";
        String description = "Описание новости delete";
        NavigationHelper.transitionToNews();
        NavigationHelper.transitionToEditNews();
        NavigationHelper.transitionToCreatingNews();

        navigationHelper.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        NavigationHelper.clickSaveButton();

        navigationHelper.clickDeleteNews(title);
        navigationHelper.checkThatControlPanelContentIsFull();
        navigationHelper.checkIfNoNewsWithTitle(title);
    }
    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        NavigationHelper.logOutOfYourAccount();
    }
}

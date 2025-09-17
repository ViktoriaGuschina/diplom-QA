package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.helpers.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.getCurrentTime;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.page.QuotesPage;

public class CreatingNewsTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    DataHelper dataHelper = new DataHelper();
    NavigationHelper navigationHelper = new NavigationHelper();
    CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
    QuotesPage quotesPage = new QuotesPage();

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
            navigationHelper.transitionToNews();
            navigationHelper.transitionToEditNews();
            navigationHelper.transitionToCreatingNews();
        } catch (Exception E) {
        }
    }
    @Test
//    @Feature(value = "Тесты раздела Новости")
//    @Story("Переход к созданию новости и наличие всех элементов")
    public void shouldOpenCreateNews() {
        navigationHelper.checkThatCreateNewsPageContentIsFull();
        navigationHelper.clickCancelButton();
        navigationHelper.clickOKButton();
    }

    @Test
//    @Feature(value = "Тесты раздела Новости")
//    @Story("Cоздание новости с пустыми данными")
    public void shouldCreateEmptyNews() {
        AtomicReference<String> errorString = new AtomicReference<>();
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            errorString.set(activity.getApplicationContext().getString(R.string.error));
        });

        NavigationHelper.clickSaveButton();


        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject toast = device.findObject(new UiSelector().text(errorString.get()));

        toast.exists();
        navigationHelper.clickCancelButton();
        navigationHelper.clickOKButton();
    }

    @Test
//    @Feature(value = "Тесты раздела Новости")
//    @Story("Cоздание новости с валидными данными")
    public void shouldCreateNewsValid() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость дня";
        String description = "Описание новости тест";

        navigationHelper.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        NavigationHelper.clickSaveButton();


        navigationHelper.checkIfNewsWithTitle(title);
    }

    @Test
//    @Feature(value = "Тесты раздела Новости")
//    @Story("Отмена создания новости")
    public void shouldCancelCreateNews() {
        navigationHelper.clickCancelButton();
        navigationHelper.clickOKButton();
        navigationHelper.checkThatControlPanelContentIsFull();
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);

        NavigationHelper.logOutOfYourAccount();
    }
}

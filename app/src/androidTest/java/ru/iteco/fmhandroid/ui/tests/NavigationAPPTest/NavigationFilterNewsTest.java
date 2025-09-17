package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.FilterHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;

public class NavigationFilterNewsTest {
    DataHelper dataHelper = new DataHelper();
    NavigationHelper navigationHelper = new NavigationHelper();

    FilterHelper filterHelper = new FilterHelper();

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
        } catch (Exception E) {
        }

//        NavigationHelper.transitionToNews();
//        NavigationHelper.transitionToEditNews();
//        NavigationHelper.goToNewsFilterSettings();
    }

    @Test
    public void transitionToCreatingNewsTest() throws InterruptedException{
        NavigationHelper.transitionToNews();
        NavigationHelper.transitionToEditNews();
        NavigationHelper.goToNewsFilterSettings();
//            // Получить AutoCompleteTextView и открыть выпадающий список
//            onView(withId(R.id.news_item_category_text_auto_complete_text_view))
//                    .perform(click());
//
//            // Подождать немного, чтобы список появился, или использовать IdlingResource, лучше избегать sleep
//            Thread.sleep(1000);
//
//            // Выбрать один из элементов (например, "Категория 2")
//            Espresso.onData(Matchers.equalTo("Категория 2"))
//                    .inRoot(Matchers.isOneOf())
//                    .perform(click());
//
//            // Проверить, что выбранное значение отображается в самом AutoCompleteTextView
//            onView(withId(R.id.news_item_category_text_auto_complete_text_view))
//                    .check(matches(withText("Категория 2")));
        }
        @After
        public void close () {
            IdlingRegistry.getInstance().unregister(idlingResource);

            NavigationHelper.logOutOfYourAccount();
        }
    }

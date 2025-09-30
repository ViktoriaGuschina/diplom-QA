package ru.iteco.fmhandroid.ui.tests.NavigationAPPTest;

import static androidx.test.espresso.Espresso.onView;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static io.qameta.allure.android.AllureScreenshotKt.allureScreenshot;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.Rand.randomCategory;

import android.widget.TextView;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.helpers.RecyclerViewMatcher;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@Epic("Фильтр новостей")
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
        try {
            AuthorizationSteps.auth();
            MainSteps.transitionToNews();
            FilterNewsSteps.goToNewsFilterSettings();
        } catch (Exception E) {
        }
    }

    @Test
    public void transitionToCreatingNewsTest() {
        String category = randomCategory();
        FilterNewsSteps.fillInNewsCategoryField(category);

        Calendar calStart = DataHelper.getCalendarWithOffset(-7);
        Calendar calEnd = DataHelper.getCalendarWithOffset(0);

        DataHelper.selectDateInDatePicker(calStart, R.id.news_item_publish_date_start_text_input_edit_text);
        DataHelper.selectEndDateInDatePicker(calEnd, R.id.news_item_publish_date_end_text_input_edit_text);

        FilterNewsSteps.clickFilterButton();

        Date startDate = calStart.getTime();
        Date endDate = calEnd.getTime();

        AtomicReference<String> newsDateStr = new AtomicReference<>();
        onView(RecyclerViewMatcher.withRecyclerView(R.id.news_list_recycler_view)
                .atPositionOnView(0, R.id.news_item_date_text_view))
                .check((view, noViewFoundException) -> {
                    if (noViewFoundException != null) throw noViewFoundException;
                    TextView dateTextView = (TextView) view;
                    newsDateStr.set(dateTextView.getText().toString());
                });

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date newsDate = sdf.parse(newsDateStr.get());

            assertTrue("Дата новости должна быть >= startDate", !newsDate.before(startDate));
            assertTrue("Дата новости должна быть <= endDate", !newsDate.after(endDate));
        } catch (ParseException e) {
            fail("Не удалось распарсить дату новости: " + newsDateStr.get());
        }


        allureScreenshot("transition To Creating News Test", 100, 1.0f);
    }

    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        try {
            AuthorizationSteps.logOut();
        } catch (Exception e) {
        }
    }
}

package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    public ViewInteraction trandemak;
    public ViewInteraction title;
    public static ViewInteraction sortButton;
    public ViewInteraction filterButton;
    public static ViewInteraction controlPanelButton;
    public static ViewInteraction childNewsButton;
    public Matcher<View> recyclerList;
    static public ViewInteraction addNewsImageMatcher;

    public NewsPage() {
        trandemak = onView(withId(R.id.trademark_image_view));
        title = onView(withText("Новости"));
        sortButton = onView(withId(R.id.sort_news_material_button));
        filterButton = onView(withId(R.id.filter_news_material_button));
        recyclerList = withId(R.id.news_list_recycler_view);
        controlPanelButton = onView(withId(R.id.edit_news_material_button));
        childNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                        0)));
        addNewsImageMatcher = onView(withId(R.id.add_news_image_view));
    }
}
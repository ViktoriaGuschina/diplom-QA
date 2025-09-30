package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {

    static public ViewInteraction filterButton;
    public static ViewInteraction cancelButton;
    public ViewInteraction title;
    public ViewInteraction categoryField;
    public static ViewInteraction dateStartPublish;
    public static ViewInteraction dateEndPublish;
    static public ViewInteraction okButtonMessage;
    public ViewInteraction cancelButtonMessage;
    public static ViewInteraction newsListRecyclerView;

    public FilterNewsPage() {

        newsListRecyclerView = onView(withId(R.id.news_list_recycler_view));
        filterButton = onView(withId(R.id.filter_button));
        cancelButton = onView(withId(R.id.cancel_button));
        title = onView(withId(R.id.filter_news_title_text_view));
        categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        dateStartPublish = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        dateEndPublish = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
        okButtonMessage = onView(withText("OK"));
        cancelButtonMessage = onView(withText("Cancel"));
    }
}

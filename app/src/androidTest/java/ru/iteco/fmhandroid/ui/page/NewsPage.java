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
    public ViewInteraction logo;
    public ViewInteraction title;
    public ViewInteraction sort;
    public ViewInteraction filter;
    public ViewInteraction controlPanelButton;
    public ViewInteraction allNewsBlock;
    public ViewInteraction childNewsButton;
    public Matcher<View> recyclerMatcher;
    private final Matcher<View> itemTitleMatcher;
    private final Matcher<View> itemDescriptionMatcher;
    public ViewInteraction addNewsImageMatcher;

    public NewsPage() {
        logo = onView(withId(R.id.trademark_image_view));
        title = onView(withText("Новости"));
        sort = onView(withId(R.id.sort_news_material_button));
        filter = onView(withId(R.id.filter_news_material_button));
        recyclerMatcher = withId(R.id.news_list_recycler_view);
        itemTitleMatcher = withId(R.id.news_item_title_text_view);
        itemDescriptionMatcher = withId(R.id.news_item_description_text_view);
        controlPanelButton = onView(withId(R.id.edit_news_material_button));
        allNewsBlock = onView(withId(R.id.all_news_cards_block_constraint_layout));
        childNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                        0)));
        addNewsImageMatcher = onView(withId(R.id.add_news_image_view));
    }

    public ViewInteraction getNewsItemTitle(int index)  {
        return onView(withIndex(itemTitleMatcher, index));
    }

    public ViewInteraction getNewsItemDescription(int index)  {
        return onView(withIndex(itemDescriptionMatcher, index));
    }
}
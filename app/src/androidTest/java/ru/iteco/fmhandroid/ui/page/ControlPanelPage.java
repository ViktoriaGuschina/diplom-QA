package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage {

    public static ViewInteraction trademark;
    public ViewInteraction title;
    public ViewInteraction newsList;
    public static ViewInteraction sortNewsButton;
    public static ViewInteraction filterButton;
    public static ViewInteraction addNewsButton;
    public ViewInteraction newsTitleText;
    public ViewInteraction newsDescriptionText;
    public ViewInteraction newsPublicationDate;
    public ViewInteraction newsCreationDate;
    public ViewInteraction author;
    public ViewInteraction statusActive;
    public ViewInteraction statusNotActive;
    public Matcher<View> addNewsButtonMatcher;

    public ControlPanelPage() {

        newsCreationDate = onView(withId(R.id.news_item_creation_text_view));
        author = onView(withId(R.id.news_item_author_text_view));
        statusActive =
                onView(withIndex(withId(R.id.news_item_published_text_view), 0));
        statusNotActive =
                onView(withIndex(withId(R.id.news_item_published_text_view), 0));
        trademark = onView(withId(R.id.trademark_image_view));
        title = onView(withText("Control panel"));
        newsList = onView(withId(R.id.news_list_recycler_view));
        newsTitleText =
                onView(withIndex(withId(R.id.news_item_title_text_view), 0));
        sortNewsButton = onView(withId(R.id.sort_news_material_button));
        filterButton = onView(withId(R.id.filter_news_material_button));
        addNewsButton = onView(withId(R.id.add_news_image_view));
        addNewsButtonMatcher = withId(R.id.add_news_image_view);
        newsDescriptionText = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
        newsPublicationDate = onView(withIndex(withId(R.id.news_item_publication_text_view), 0));

    }

    public static ViewInteraction deleteNewsButton(String newsTitle) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(newsTitle))))))));
    }


}
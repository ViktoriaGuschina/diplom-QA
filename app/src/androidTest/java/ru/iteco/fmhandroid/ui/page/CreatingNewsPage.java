package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreatingNewsPage {

    public ViewInteraction title;
    public ViewInteraction categoryField;
    public ViewInteraction titleField;
    public ViewInteraction descriptionField;
    public ViewInteraction publicationDateField;
    public ViewInteraction timeField;
    public ViewInteraction switcher;
    public ViewInteraction saveButton;
    public static ViewInteraction cancelButton;
    public ViewInteraction errorMessage;
    public ViewInteraction okButtonMessage;
    public ViewInteraction cancelButtonMessage;

    public CreatingNewsPage() {

        title = onView(withId(R.id.custom_app_bar_title_text_view));
        categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        titleField = onView(withId(R.id.news_item_title_text_input_edit_text));
        descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
        publicationDateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
        timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
        switcher = onView(withId(R.id.switcher));
        saveButton = onView(withId(R.id.save_button));
        cancelButton = onView(withId(R.id.cancel_button));
        errorMessage = onView(withId(R.id.message));
        okButtonMessage = onView(withText("OK"));
        cancelButtonMessage = onView(withText("Cancel"));
    }
}


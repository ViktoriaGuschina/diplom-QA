package ru.iteco.fmhandroid.ui.helpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class FilterHelper {

    public static void buttonCategory() {
        onView(
                withId(R.id.text_input_end_icon)
        ).perform(click());
    }
}

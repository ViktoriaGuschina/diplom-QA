package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.helpers.DataHelper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    public ViewInteraction trademark;
    public ViewInteraction title;
    public static ViewInteraction ourMissionList;
    public ViewInteraction missionConstraintLayout;
    private final Matcher<View> ourMissionItemDescriptionMatcher;

    public OurMissionPage() {
        trademark = onView(withId(R.id.trademark_image_view));
        title = onView(withId(R.id.our_mission_title_text_view));
        ourMissionItemDescriptionMatcher = withId(R.id.our_mission_item_description_text_view);
        ourMissionList = onView(withId(R.id.our_mission_item_list_recycler_view));
        missionConstraintLayout = onView(
                allOf(
                        withId(R.id.our_mission_item_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0
                        )
                )
        );
    }

    public static ViewInteraction buttonOurMission() {
        return onView(withId(R.id.our_mission_image_button));
    }

    public ViewInteraction getOurMissionItemDescription(String text) {
        return onView(allOf(
                ourMissionItemDescriptionMatcher,
                withText(text),
                isCompletelyDisplayed()));
    }
}

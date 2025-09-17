package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
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

public class QuotesPage {
    public ViewInteraction logo;
    public ViewInteraction title;
    public ViewInteraction ourMissionList;
    public ViewInteraction missionConstraintLayout;
    private final Matcher<View> ourMissionItemDescriptionMatcher;

    public QuotesPage() {
        // Логотип
        logo = onView(withId(R.id.trademark_image_view));
        // Заголовок
        title = onView(withId(R.id.our_mission_title_text_view));
        // Мастер-майнд для описания элемента миссии
        ourMissionItemDescriptionMatcher = withId(R.id.our_mission_item_description_text_view);
        // RecyclerView со списком миссий
        ourMissionList = onView(withId(R.id.our_mission_item_list_recycler_view));
        // Обёртка-контейнер для RecyclerView внутри ConstraintLayout
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
}

//    public ViewInteraction getOurMissionItemDescription(String text) {
//        return onView(allOf(
//                ourMissionItemDescriptionMatcher,
//                withText(text),
//                isCompletelyDisplayed()));
//    }
//}

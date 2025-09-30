package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

public class OurMissionSteps {
    static OurMissionPage ourMissionPage = new OurMissionPage();

    public static void scroll() {
        Allure.step("Пролистывание ленты до низа");
       OurMissionPage.ourMissionList
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isAssignableFrom(RecyclerView.class);
                    }

                    @Override
                    public String getDescription() {
                        return "Scroll RecyclerView to bottom";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        RecyclerView recyclerView = (RecyclerView) view;
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        if (adapter != null) {
                            int lastPosition = adapter.getItemCount() - 1;
                            recyclerView.scrollToPosition(lastPosition);
                            uiController.loopMainThreadUntilIdle();
                        }
                    }
                });
    }
    public static void checkOurMission(int number) {
        Allure.step("Свернуть/развернуть цитату");
        ourMissionPage.missionConstraintLayout.check(matches(isDisplayed()));
        ourMissionPage.missionConstraintLayout.perform(actionOnItemAtPosition(number, click()));
    }

    public static void descriptionIsDisplay(String text) {
        Allure.step("Отображение дополнительной цитаты");
        ourMissionPage.getOurMissionItemDescription(text).check(matches(isDisplayed()));
    }

    public static void checkThatOurMissionBlockContentIsFull() {
        Allure.step("Проверка блока с цитатами на полный контент");
        ourMissionPage.trademark.check(matches(isDisplayed()));
        ourMissionPage.title.check(matches(isDisplayed()));
        ourMissionPage.ourMissionList.check(matches(isDisplayed()));
    }

    public static void checkingTitleOurMission() {
        Allure.step("Наличие тайтла Quotes");
        NavigationHelper.textDisplayTitle(R.id.our_mission_title_text_view);
    }
}

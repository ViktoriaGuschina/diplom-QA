package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static ViewInteraction trademark;
    public static ViewInteraction authButton;
    public static ViewInteraction menuButton;
    public static ViewInteraction logOutButton;
    public static ViewInteraction maimMenu;
    public static ViewInteraction newsMenu;
    public static ViewInteraction aboutMenu;
    public static ViewInteraction ourMissionButton;
    public static ViewInteraction allNewsButton;

    public MainPage() {
        trademark = onView(withId(R.id.trademark_image_view));
        authButton = onView(withId(R.id.authorization_image_button));
        logOutButton = onView(withText("Log out"));
        menuButton = onView(withId(R.id.main_menu_image_button));
        maimMenu = onView(withText("Main"));
        newsMenu = onView(withText("News"));
        aboutMenu = onView(withText("About"));
        ourMissionButton = onView(withId(R.id.our_mission_image_button));
        allNewsButton = onView(withId(R.id.all_news_text_view));
    }
}

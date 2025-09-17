package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public ViewInteraction mainLogo;
    public ViewInteraction profileButton;
    public ViewInteraction logOutButton;
    public ViewInteraction menuButton;
    public ViewInteraction mainOfMenu;
    public ViewInteraction newsOfMenu;
    public ViewInteraction aboutOfMenu;
    public ViewInteraction ourMissionButton;
    public ViewInteraction titleOfNewsContainer;
    public ViewInteraction allNewsButton;
    public ViewInteraction collapseAllNewsButton;
    public ViewInteraction allNewsTextMatcher;

    public MainPage() {
        mainLogo = onView(withId(R.id.trademark_image_view));
        profileButton = onView(withId(R.id.authorization_image_button));
        logOutButton = onView(withText("Log out"));
        menuButton = onView(withId(R.id.main_menu_image_button));
        mainOfMenu = onView(withText("Main"));
        newsOfMenu = onView(withText("News"));
        aboutOfMenu = onView(withText("About"));
        ourMissionButton = onView(withId(R.id.our_mission_image_button));
        titleOfNewsContainer = onView(withText("News"));
        allNewsTextMatcher = onView(withId(R.id.all_news_text_view));
     //   allNewsButton = onView(allNewsTextMatcher);
        collapseAllNewsButton = onView(withId(R.id.expand_material_button));
    }
}

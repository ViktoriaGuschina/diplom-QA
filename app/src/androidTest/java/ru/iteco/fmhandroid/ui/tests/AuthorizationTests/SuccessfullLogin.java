package ru.iteco.fmhandroid.ui.tests.AuthorizationTests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helpers.AuthorizationHelper;
import ru.iteco.fmhandroid.ui.helpers.DataHelper;
import ru.iteco.fmhandroid.ui.helpers.NavigationHelper;
import ru.iteco.fmhandroid.ui.helpers.ProgressIndicatorIdlingResource;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class SuccessfullLogin {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    DataHelper dataHelper = new DataHelper();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    public static ProgressIndicatorIdlingResource idlingResource;

    @Before
    public void setup() {
        idlingResource = new ProgressIndicatorIdlingResource(R.id.splash_screen_circular_progress_indicator);
        IdlingRegistry.getInstance().register(idlingResource);
        try {
            NavigationHelper.logOutOfYourAccount();
        } catch (Exception E) {
        }
    }

    @Test
    public void shouldBeFullContentInAboutBlock() {
        AuthorizationHelper.checkThatAuthorizationBlockContentIsFull();
    }

    @Test
    public void successFullLoginTest(){
        NavigationHelper.textDisplayTitle(R.id.auth);
        AuthorizationHelper.auth();
    }
    @After
    public void close() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

}

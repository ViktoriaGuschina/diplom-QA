package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.ViewInteraction;
import android.view.View;
import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    public ViewInteraction title;
    public ViewInteraction loginField;
    public ViewInteraction passwordField;
    public ViewInteraction loginButton;
    public Matcher<View> loginButtonMatcher;

    public AuthorizationPage() {
        title = onView(withText("Authorization"));
        loginField = onView(withId(R.id.login_text_input_edit_text));
        passwordField = onView(withId(R.id.password_text_input_edit_text));
        loginButtonMatcher = withId(R.id.enter_button);
        loginButton = onView(loginButtonMatcher);
    }
}

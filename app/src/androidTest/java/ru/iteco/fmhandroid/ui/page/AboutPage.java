package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    public ViewInteraction termsOfUseText;
    public ViewInteraction infoCompanyLabel;
    public ViewInteraction privacyPolicyLink;
    public ViewInteraction linkTermsOfUse;
    public Matcher<View> infoCompanyLabelMatcher;
    public ViewInteraction trademark;
    public ViewInteraction backButton;
    public ViewInteraction versionText;
    public ViewInteraction applicationVersionNumber;
    public ViewInteraction privacyPolicyText;

    public AboutPage() {
        termsOfUseText = onView(withId(R.id.about_terms_of_use_label_text_view));
        infoCompanyLabelMatcher = withId(R.id.about_company_info_label_text_view);
        infoCompanyLabel = onView(infoCompanyLabelMatcher);
        privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
        linkTermsOfUse = onView(withId(R.id.about_terms_of_use_value_text_view));
        trademark = onView(withId(R.id.trademark_image_view));
        backButton = onView(withId(R.id.about_back_image_button));
        versionText = onView(withId(R.id.about_version_title_text_view));
        applicationVersionNumber = onView(withId(R.id.about_version_value_text_view));
        privacyPolicyText = onView(withId(R.id.about_privacy_policy_label_text_view));
    }
}
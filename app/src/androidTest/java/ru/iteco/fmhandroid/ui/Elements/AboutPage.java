package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    public static ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    public static ViewInteraction versionValue = onView(withId(R.id.about_version_value_text_view));
    public static ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    public static ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_label_text_view));
    public static ViewInteraction termsOfUseLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    public static ViewInteraction companyInfo = onView(withId(R.id.about_company_info_label_text_view));
    public static ViewInteraction returnBtn = onView(withId(R.id.about_back_image_button));
    public static ViewInteraction tradeMarkImage = onView(withId(R.id.trademark_image_view));
}

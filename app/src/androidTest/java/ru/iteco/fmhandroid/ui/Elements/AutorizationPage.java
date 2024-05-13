package ru.iteco.fmhandroid.ui.Elements;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AutorizationPage {
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));
    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction passwordField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction authorizationButton = onView(withId(R.id.authorization_image_button));

}

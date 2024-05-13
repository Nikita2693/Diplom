package ru.iteco.fmhandroid.ui.Steps;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.Elements.AutorizationPage.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.AutorizationPage;

public class AuthorizationSteps {
    public void logIn(String login, String password) throws InterruptedException {
        Allure.step("Авторизация в приложении");

        loginField.check(matches(isDisplayed()));
        onView(withHint("Login")).perform(typeText(login), closeSoftKeyboard());

        passwordField.check(matches(isDisplayed()));
        onView(withHint("Password")).perform(typeText(password), closeSoftKeyboard());

        signInButton.check(matches(isDisplayed()));
        signInButton.perform(click());
        Thread.sleep(1000);

    }
    public void logOut () {
        Allure.step("Разлогинется в приложении");
        authorizationButton.perform(click());
        onView(allOf(withId(android.R.id.title),
                childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        0),
                isDisplayed())).perform(click());
        signInButton.check(matches(isDisplayed()));
    }

    public void checkTextAuthorization() {
        Allure.step("Проверка что залогинились");
        authorizationButton.check(matches(isDisplayed()));
    }

    public void errorPopupWindow () {
        ViewInteraction imageView = onView(
                allOf(withContentDescription("app background image"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

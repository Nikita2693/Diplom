package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.Elements.MainPage.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainSteps {

    public void clickMainMenu () {
        Allure.step("Кликнуть основное меню");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
    }

    public void ClickNewsInMenu() {
        Allure.step("Кликнуть название экрана Новости в выпадающем списке");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        newsOfMenu.perform(click());
    }

    public void ClickAboutInMenu() {
        Allure.step("Кликнуть название экрана О приложении в выпадающем списке");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        aboutOfMenu.perform(click());
    }

    public void ClickMainInMenu() {
        Allure.step("Кликнуть название экрана Главная в выпадающем списке");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        mainOfMenu.perform(click());
    }

    public void ClickOurMission() {
        Allure.step("Кликнуть кнопку наша миссия");
        ourMission.check(matches(isDisplayed()));
        ourMission.perform(click());
    }

    public void ExpandAllNews () {
        Allure.step("Кликнуть все новости");
        expandNewsFeedButton.check(matches(isDisplayed()));
        expandNewsFeedButton.perform(click());
    }
    public void ExpandSinglNews () {
        newsUnit.check(matches(isDisplayed()));
        newsUnit.perform(click());
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

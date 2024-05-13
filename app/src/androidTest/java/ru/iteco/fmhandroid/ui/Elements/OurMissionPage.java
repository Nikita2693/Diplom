package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    public static ViewInteraction loveIsAll = onView(withText("Love is all"));
    public static ViewInteraction missionList = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
            childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));
    public static ViewInteraction quoteTitle = onView(allOf(withId(R.id.our_mission_item_title_text_view)));
    public static ViewInteraction quoteBody (String text) {
        return onView(allOf(withId(R.id.our_mission_item_description_text_view), withText(text)));
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

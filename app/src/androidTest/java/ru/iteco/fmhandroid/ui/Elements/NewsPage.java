package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    public static ViewInteraction titleOfNewsBlock = onView(withText("News"));

    public static ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction allNewsList = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public static ViewInteraction editButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction newsTitle (int number) {
        return onView(withIndex(withId(R.id.news_item_title_text_view), number));
    }
    public static ViewInteraction newsBody  (int number) {
        return onView(withIndex(withId(R.id.news_item_description_text_view), number));
    }
    public static ViewInteraction newsData (int number) {
        return onView(withIndex(withId(R.id.news_item_date_text_view), number));
    }
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction okButton = onView(withId(android.R.id.button1));
    public static ViewInteraction newsList = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withId(R.id.all_news_cards_block_constraint_layout),
                            0)));
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

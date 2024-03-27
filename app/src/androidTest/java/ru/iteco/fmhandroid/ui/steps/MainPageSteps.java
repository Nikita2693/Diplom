package ru.iteco.fmhandroid.ui.steps;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import ru.iteco.fmhandroid.R;


public class MainPageSteps {
    //отображение главной страницы
    public static void homePageDisplay() {
        onView(allOf(withText("Новости"),
                withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main))),
                isDisplayed())).check(matches(withText("Новости")));
        onView(allOf(withText("Заявки"),
                withParent(withParent(withId(R.id.container_list_claim_include_on_fragment_main))),
                isDisplayed())).check(matches(withText("Заявки")));
    }

    //нажать кнопку "свернуть/развернуть" новости
    public static void clickTheExpandNewsButton() {
        onView(allOf(withId(R.id.all_news_text_view), withText("ВСЕ НОВОСТИ"),
                withParent(allOf(withId(R.id.container_list_news_include_on_fragment_main),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                isDisplayed())).check(matches(withText("ВСЕ НОВОСТИ")));
        onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.container_list_news_include_on_fragment_main),
                                0),
                        4),
                isDisplayed())).perform(click());
        onView(allOf(withId(R.id.all_news_text_view), withText("ВСЕ НОВОСТИ"),
                withParent(allOf(withId(R.id.container_list_news_include_on_fragment_main),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                isDisplayed())).check(doesNotExist());
    }

    //нажать кнопку "свернуть/развернуть" заявки
    public static void clickTheExpandClaimsButton() {
        onView(allOf(withId(R.id.all_claims_text_view), withText("ВСЕ ЗАЯВКИ"),
                withParent(allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                isDisplayed())).check(matches(withText("ВСЕ ЗАЯВКИ")));
        onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.container_list_claim_include_on_fragment_main),
                                0),
                        3),
                isDisplayed())).perform(click());
        onView(allOf(withId(R.id.all_claims_text_view), withText("ВСЕ ЗАЯВКИ"),
                withParent(allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                isDisplayed())).check(doesNotExist());
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

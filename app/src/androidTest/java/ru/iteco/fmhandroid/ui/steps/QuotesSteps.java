package ru.iteco.fmhandroid.ui.steps;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
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

public class QuotesSteps {

    //Открытие страницы "Цитата дня"
    public static void openPageQuote() throws InterruptedException {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.our_mission_image_button), withContentDescription("Наша Миссия"),
                        childAtPosition(
                                allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText("Главное - жить любя")));
    }

    //Нажать на кнопку "развернуть"
    public static void buttonExpand() throws InterruptedException {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.our_mission_image_button), withContentDescription("Наша Миссия"),
                        childAtPosition(
                                allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.our_mission_item_title_text_view), withText("«Хоспис для меня - это то, каким должен быть мир.\""),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        textView2.check(matches(withText("«Хоспис для меня - это то, каким должен быть мир.\"")));
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.our_mission_item_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.our_mission_item_description_text_view), withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер"),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        textView3.check(matches(withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер")));
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

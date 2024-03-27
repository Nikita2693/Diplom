package ru.iteco.fmhandroid.ui.steps;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;
public class ClaimsFilteringSteps {
    //фильтрация по статусу "Открыта"
    public static void filterStatusOpen() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("Все заявки"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Меню фильтрации списка заявок"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("В работе"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox.perform(scrollTo(), click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton3.perform(scrollTo(), click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Открыта")));
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("кнопка закрытия экранной формы"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Открыта")));
    }

    //фильтрация по статусу "В работе"
    public static void filterStatusInProgress() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("Все заявки"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Меню фильтрации списка заявок"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.item_filter_open), withText("Открыта"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox4.perform(scrollTo(), click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton3.perform(scrollTo(), click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("В работе")));
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("кнопка закрытия экранной формы"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("В работе")));
    }

    //фильтрация по статусу "Выполнена"
    public static void filterStatusCompleted() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("Все заявки"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Меню фильтрации списка заявок"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.item_filter_open), withText("Открыта"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox4.perform(scrollTo(), click());
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("В работе"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox.perform(scrollTo(), click());
        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.item_filter_executed), withText("Выполнена"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialCheckBox5.perform(scrollTo(), click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton3.perform(scrollTo(), click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Выполнена")));
        Thread.sleep(2000);
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("кнопка закрытия экранной формы"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Выполнена")));
    }

    //фильтрация по статусу "Отмененные"
    public static void filterStatusCanceled() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("Все заявки"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Меню фильтрации списка заявок"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.item_filter_open), withText("Открыта"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox4.perform(scrollTo(), click());
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("В работе"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox.perform(scrollTo(), click());
        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Отмененные"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialCheckBox5.perform(scrollTo(), click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton3.perform(scrollTo(), click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Отменена")));
        Thread.sleep(2000);
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("кнопка закрытия экранной формы"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));
        Thread.sleep(2000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Отменена")));
    }

    public static void filterStatusNotSelected() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("Все заявки"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Меню фильтрации списка заявок"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.item_filter_open), withText("Открыта"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox4.perform(scrollTo(), click());
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("В работе"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox.perform(scrollTo(), click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton3.perform(scrollTo(), click());
        Thread.sleep(2000);
        onView(withId(R.id.empty_claim_list_text_view)).check(matches(withText("Здесь пока ничего нет…")));
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

package ru.iteco.fmhandroid.ui.steps;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
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
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class NewsCreationSteps {

    //открытие страницы "Новости"
    public static void creatingNewsOpening() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_news_text_view), withText("Все новости"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_news_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction textView = onView(
                allOf(withText("Новости"),
                        withParent(withParent(withId(R.id.container_list_news_include))),
                        isDisplayed()));
        textView.check(matches(withText("Новости")));
    }

    //создание новости с валидными значениями
    public static void creatingNewsWithValidVal() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.edit_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Кнопка добавления новости"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_category_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView2.perform(replaceText("Объявление"), closeSoftKeyboard());
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_create_date_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());
        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_time_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());
        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText("Моё объявление"), closeSoftKeyboard());
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText("описание"), closeSoftKeyboard());
        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.save_button), withText("Сохранить"), withContentDescription("Сохранить"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton6.perform(scrollTo(), click());
        pressBack();
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_news_cards_block_constraint_layout),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.news_item_description_text_view), withText("описание"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView2.check(matches(withText("описание")));
    }

    //создание новости c пустыми полями
    public static void creatingNewsClear() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.edit_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Кнопка добавления новости"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());
        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.save_button), withText("Сохранить"), withContentDescription("Сохранить"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton6.perform(scrollTo(), click());
        ViewInteraction textView = onView(
                allOf(withId(R.id.custom_app_bar_title_text_view), withText("Создание"),
                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_news),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Создание")));
    }

    //создание новости с валидными значениями публикация через год
    public static void creatingNewsWithValidValAyearLater() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.edit_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Кнопка добавления новости"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_category_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView2.perform(replaceText("День рождения"), closeSoftKeyboard());
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_create_date_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction materialTextView4 = onView(
                allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText("2023"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView4.perform(click());
        DataInteraction materialTextView5 = onData(anything())
                .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
                        childAtPosition(
                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                1)))
                .atPosition(1);
        materialTextView5.perform(scrollTo(), click());
        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_time_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());
        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("ОК"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText("Мой день рождения"), closeSoftKeyboard());
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText("день рождения"), closeSoftKeyboard());
        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.save_button), withText("Сохранить"), withContentDescription("Сохранить"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton6.perform(scrollTo(), click());
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view), withText("день рождения"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("день рождения")));
    }

    //фильтрация новостей (определяем по только что созданной новости в "Объявления")
    public static void checkFilteringCreatingNews() throws InterruptedException {
        Thread.sleep(2000);
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_start_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_end_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());
        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_category_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView2.perform(replaceText("Объявление"), closeSoftKeyboard());
        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.filter_button), withText("Фильтровать"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton5.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view), withText("описание"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("описание")));
    }

    //фильтрация новостей по несуществующей категории
    public static void checkFilteringNewsNonExistentCategory() throws InterruptedException {
        Thread.sleep(2000);
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_start_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());
        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_end_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());
        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_category_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView2.perform(replaceText("Несуществующее"), closeSoftKeyboard());
        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.filter_button), withText("Фильтровать"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton5.perform(click());
        Thread.sleep(2000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view), withText("описание"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("описание")));
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

package ru.iteco.fmhandroid.ui.steps;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
import static org.hamcrest.Matchers.is;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import ru.iteco.fmhandroid.R;

public class EditingNewsSteps {
    //открытие страницы "Панель управления" (редактирования новостей)
    public static void newsOpeningEditor() throws InterruptedException {
        Thread.sleep(5000);
        onView(allOf(withId(R.id.edit_news_material_button),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.container_list_news_include),
                                0),
                        3),
                isDisplayed())).
                perform(click());
        onView(allOf(withText("Панель \n управления"),
                withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                isDisplayed()))
                .check(matches(withText("Панель \n управления")));
    }

    //изменение статуса "Активна\Не активна"
    public static void fromActiveToInactive() throws InterruptedException {
        Thread.sleep(2000);
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0))
                .check(matches(withText("АКТИВНА")));
        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_material_card_view),
                                        0),
                                15),
                        isDisplayed()), 0))
                .perform(click());
        onView(allOf(withId(R.id.switcher), withText("Активна"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("com.google.android.material.card.MaterialCardView")),
                                0),
                        5)))
                .perform(scrollTo(), click());
        onView(allOf(withId(R.id.save_button), withText("Сохранить"), withContentDescription("Сохранить"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("com.google.android.material.card.MaterialCardView")),
                                0),
                        6)))
                .perform(scrollTo(), click());
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0)).check(matches(withText("НЕ АКТИВНА")));
    }

    //отмена удаления новости (статус "Не активна")
    public static void cancelDeleteNews() throws InterruptedException {
        Thread.sleep(2000);
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0))
                .check(matches(withText("НЕ АКТИВНА")));
        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view), withContentDescription("Кнопка удаления новости"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_material_card_view),
                                        0),
                                14),
                        isDisplayed()), 0))
                .perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Вы уверены, что хотите безвозвратно удалить документ? Данные изменения нельзя будет отменить в будущем.")));
        onView(allOf(withId(android.R.id.button2), withText("Отмена"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.ScrollView")),
                                0),
                        2)))
                .perform(scrollTo(), click());
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0)).check(matches(withText("НЕ АКТИВНА")));
    }

    //Удаление новости (статус "Не активна")
    public static void deleteNewsInactiv() throws InterruptedException {
        Thread.sleep(2000);
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0))
                .check(matches(withText("НЕ АКТИВНА")));
        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view), withContentDescription("Кнопка удаления новости"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_material_card_view),
                                        0),
                                14),
                        isDisplayed()), 0))
                .perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Вы уверены, что хотите безвозвратно удалить документ? Данные изменения нельзя будет отменить в будущем.")));
        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.ScrollView")),
                                0),
                        3)))
                .perform(scrollTo(), click());
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), 0))
                .check(matches(withText("АКТИВНА")));
    }

    //Редактирование новости
    public static void editNews() throws InterruptedException {
        Thread.sleep(2000);
        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_material_card_view),
                                        0),
                                15),
                        isDisplayed()), 0))
                .perform(click());
        onView(allOf(withId(R.id.news_item_description_text_input_edit_text),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.news_item_description_text_input_layout),
                                0),
                        1),
                isDisplayed()))
                .perform(replaceText("редактирование"));
        onView(allOf(withId(R.id.save_button), withText("Сохранить"), withContentDescription("Сохранить"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("com.google.android.material.card.MaterialCardView")),
                                0),
                        6)))
                .perform(scrollTo(), click());
        onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(
                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                        0)))
                .perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText("редактирование"),
                withParent(withParent(withId(R.id.news_item_material_card_view))),
                isDisplayed()))
                .check(matches(withText("редактирование")));
    }

    //отмена редактирования новости
    public static void cancelEditNews() throws InterruptedException {
        Thread.sleep(2000);
        onView(withIndex(allOf(withId(R.id.edit_news_item_image_view),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.news_item_material_card_view),
                                0),
                        15),
                isDisplayed()), 0))
                .perform(click());
        onView(allOf(withId(R.id.news_item_description_text_input_edit_text),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.news_item_description_text_input_layout),
                                0),
                        1),
                isDisplayed()))
                .perform(replaceText("редактирование"));
        onView(allOf(withId(R.id.cancel_button), withText("Отмена"), withContentDescription("Отмена"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("com.google.android.material.card.MaterialCardView")),
                                0),
                        7)))
                .perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.message), withText("Изменения не будут сохранены. Вы действительно хотите выйти?"),
                withParent(withParent(withId(com.google.android.material.R.id.scrollView))),
                isDisplayed()))
                .check(matches(withText("Изменения не будут сохранены. Вы действительно хотите выйти?")));
        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                        childAtPosition(
                                withId(com.google.android.material.R.id.buttonPanel),
                                0),
                        3)))
                .perform(scrollTo(), click());
        onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(
                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                        0)))
                .perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText("редактирование"),
                withParent(withParent(withId(R.id.news_item_material_card_view))),
                isDisplayed()))
                .check(matches(withText("редактирование")));
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
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

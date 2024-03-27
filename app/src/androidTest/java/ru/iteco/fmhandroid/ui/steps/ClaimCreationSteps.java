package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
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
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ru.iteco.fmhandroid.R;

public class ClaimCreationSteps {
    //заполнение всех полей формы
    public static void creatingAnApplicationAllFieldsAreFilledIn() throws InterruptedException {
        Thread.sleep(8000);
        onView(withId(R.id.add_new_claim_material_button)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.title_edit_text)).perform(click(), replaceText("Новая заявка"), closeSoftKeyboard());
        onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.description_edit_text)).perform(click(), replaceText("Описание заявки"), closeSoftKeyboard());
        onView(withId(R.id.executor_drop_menu_auto_complete_text_view)).perform(click(), replaceText("Ivanov Ivan Ivanovich"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()));  //проверка появление "Главная" страница по значку авторизации
    }

    //заполнение не всех полей формы
    public static void creatingAnApplicationNotAllFieldsAreFilledIn() throws InterruptedException {
        Thread.sleep(8000);
        onView(withId(R.id.add_new_claim_material_button)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.executor_drop_menu_auto_complete_text_view)).perform(click(), replaceText("Ivanov Ivan Ivanovich"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Заполните пустые поля")));  //для проверки с пустым полем
    }

    //Отмена создания заявки
    public static void cancelingTheCreationOfTheApplication() throws InterruptedException {
        Thread.sleep(8000);
        onView(withId(R.id.add_new_claim_material_button)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Изменения не будут сохранены. Вы действительно хотите выйти?")));  //для проверки с пустым полем
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()));  //проверка появление "Главная" страница по значку авторизации    }
    }

    //Отмена ОТМЕНЫ
    public static void cancelingCancelTheCreationOfTheApplication() throws InterruptedException {
        Thread.sleep(8000);
        onView(withId(R.id.add_new_claim_material_button)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Изменения не будут сохранены. Вы действительно хотите выйти?")));  //для проверки с пустым полем
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Создание")));
    }

    //Изменение статуса заявки на "Открыта"
    public static void statusOpen() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.all_claims_text_view)).perform(click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(5000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("В работе")));
        onView(withId(R.id.status_processing_image_button)).perform(click());
        ViewInteraction materialTextView2 = onView(
                allOf(withId(android.R.id.title), withText("Сбросить"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView2.perform(click());
        onView(withId(R.id.editText)).perform(click(), replaceText("мой комментарий"), closeSoftKeyboard());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Открыта")));
    }

    //Изменение статуса заявки на "В работе"
    public static void statusInProgress() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.all_claims_text_view)).perform(click());
        Thread.sleep(5000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(5000);
        onView(withId(R.id.status_label_text_view)).check(matches(withText("Открыта")));
        onView(withId(R.id.status_processing_image_button)).perform(click());
        ViewInteraction materialTextView2 = onView(
                allOf(withId(android.R.id.title), withText("В работу"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView2.perform(click());
        onView(withId(R.id.status_label_text_view)).check(matches(withText("В работе")));
    }

    //Добавление комментария
    public static void commentToTheApplication() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.all_claims_text_view)).perform(click());
        Thread.sleep(3000);
        onView(allOf(withId(R.id.claim_list_recycler_view),
                childAtPosition(
                        withId(R.id.all_claims_cards_block_constraint_layout),
                        4))).perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.add_comment_image_button)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.comment_text_input_layout)).perform(click(), replaceText("мой комментарий"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(scrollTo(), click());
        Thread.sleep(2000);
        onView(withIndex(allOf(withId(R.id.comment_description_text_view), withText("мой комментарий"),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))),
                isDisplayed()),0)).check(matches(withText("мой комментарий")));
    }

    //Редактирование комментария
    public static void commentEditToTheApplication() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.all_claims_text_view)).perform(click());
        Thread.sleep(3000);
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(5000);
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("кнопка редактирования комментария"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());
        onView(withId(R.id.comment_text_input_layout)).perform(click(), replaceText("комментарий отредактирован"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(scrollTo(), click());
        Thread.sleep(2000);
        ViewInteraction textView = onView(
                allOf(withId(R.id.comment_description_text_view), withText("комментарий отредактирован"),
                        withParent(withParent(withId(R.id.claim_comments_list_recycler_view))),
                        isDisplayed()));
        textView.check(matches(withText("комментарий отредактирован")));
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

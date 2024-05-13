package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class EditNewsPage {
    public static ViewInteraction titleOfEditPage = onView(withText("Editing"));
    public static ViewInteraction editCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction editTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction editPublicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction editTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction editDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction statusSwitcher = onView(withId(R.id.switcher));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
}



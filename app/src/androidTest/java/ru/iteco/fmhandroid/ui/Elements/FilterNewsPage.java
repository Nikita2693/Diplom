package ru.iteco.fmhandroid.ui.Elements;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {

    public static ViewInteraction titleOfFilterPage = onView(withId(R.id.filter_news_title_text_view));
    public static ViewInteraction categoryField (int number) {
        return onView(withIndex(withId(R.id.news_item_category_text_auto_complete_text_view), number));
    }
    public static ViewInteraction startDateField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction endDateField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public static ViewInteraction titleNewsEmptySearch = onView(withId(R.id.empty_news_list_text_view));
    public static ViewInteraction titleNewsEmptySearchInControlPanel = onView(withId(R.id.control_panel_empty_news_list_text_view));
    public static ViewInteraction activeCheckBox = onView(withId(R.id.filter_news_active_material_check_box));
    public static ViewInteraction inactiveCheckBox = onView(withId(R.id.filter_news_inactive_material_check_box));
    public static ViewInteraction refreshButton = onView(withId(R.id.control_panel_news_retry_material_button));
}

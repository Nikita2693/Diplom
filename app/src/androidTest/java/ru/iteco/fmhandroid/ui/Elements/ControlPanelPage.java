package ru.iteco.fmhandroid.ui.Elements;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage {

    public static ViewInteraction titleOfControlPanelPage = onView(withText("Control panel"));
    //public static ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    //public static ViewInteraction newsFilterButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction publicationDate (int number) {
        return onView(withIndex(withId(R.id.news_item_publication_date_text_view), number));
    }
    public static ViewInteraction creationDate (int number) {
        return onView(withIndex(withId(R.id.news_item_create_date_text_view), number));
    }
    public static ViewInteraction author = onView(withId(R.id.news_item_author_name_text_view));
    public static ViewInteraction createNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction blockOfNews = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction editNews(String newsTitle) {
        return onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitle))))))));
    }
    public static ViewInteraction textSingNews (int number) {
        return onView(withIndex(withId(R.id.news_item_description_text_view), number));
    }
    public static ViewInteraction newsItemTitle ( int number) {
        return onView(withIndex(withId(R.id.news_item_title_text_view), number));
    }
    public static ViewInteraction deleteNewsButton(String newsTitle) {
        return onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitle))))))));
    }
    //public static ViewInteraction newsStatusActiveOrNotActive = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public static ViewInteraction newsStatusActiveOrNotActive (int number) {
        return onView(withIndex(withId(R.id.news_item_published_text_view), number));
    }

    public static ViewInteraction cancelButtonInDelete = onView(withId(android.R.id.button2));

}

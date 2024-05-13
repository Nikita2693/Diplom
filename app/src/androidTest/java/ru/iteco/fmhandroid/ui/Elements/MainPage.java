package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;

public class MainPage {

    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction mainOfMenu = onView(withText("Main"));
    public static ViewInteraction newsOfMenu = onView(withText("News"));
    public static ViewInteraction aboutOfMenu = onView(withText("About"));
    public static ViewInteraction ourMission = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction allNewsBtn = onView(withId(R.id.all_news_text_view));
    public static ViewInteraction expandNewsFeedButton = onView(withId(R.id.expand_material_button));
    public static ViewInteraction newsUnit = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction textSingNews = onView((withId(R.id.news_item_description_text_view)));
}

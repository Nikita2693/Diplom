package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.FileHelper.waitForElement;
import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Elements.FilterNewsPage;
import ru.iteco.fmhandroid.ui.Elements.NewsPage;
import ru.iteco.fmhandroid.ui.Elements.OurMissionPage;
import ru.iteco.fmhandroid.ui.FileHelper;

public  class NewsStep {

    public void isNewsScreen() {
        Allure.step("Проверка что мы находлимся на вкладке 'News' ");
        NewsPage.titleOfNewsBlock.check(matches(isDisplayed()));
        NewsPage.allNewsList.check(matches(isDisplayed()));
    }

    public void isControlPanelScreen () {
        Allure.step("Проверка что мы находлимся на вкладке 'Control panel' ");
        ControlPanelPage.titleOfControlPanelPage.check(matches(isDisplayed()));
    }



    public void openFilter() {
        Allure.step("Открыть окно фильтра");
        NewsPage.filterNewsButton.check(matches(isDisplayed()));
        NewsPage.filterNewsButton.perform(click());
    }

    public void clickSortButton() {
        Allure.step("Кликнуть кнопку сортироки");
        NewsPage.sortNewsButton.check(matches(isDisplayed()));
        NewsPage.sortNewsButton.perform(click());
    }

    public void clickEditButtontn() {
        Allure.step("Клинуть кнопку редактирования новости");
        NewsPage.editButton.check(matches(isDisplayed()));
        NewsPage.editButton.perform(click());
    }

    public void openNews(int number) {
        Allure.step("Раскрыть / закрыть описания новости");
        NewsPage.newsList.perform(actionOnItemAtPosition(number, click()));
    }
    public void newsContentIsDisplayed(int number) {
        Allure.step("Проверка видимости описания новости");
        NewsPage.newsBody(number).check(matches(isDisplayed()));
    }

    public void clickCancel () {
        Allure.step("Кликнуть на отмену");
        NewsPage.cancelButton.perform(click());
    }
    public int findNews (String FindText, int number){
        int numberOfSwipe = 0;
        boolean massen = true;
        while (massen){
            String firstNewsTitleAfterSecondSorting = FileHelper.Text.getText(NewsPage.newsTitle(number));
            if (FindText.equals(firstNewsTitleAfterSecondSorting)){
                massen = false;
            }
            else if (number == 3)
            {
                number = 0;
                numberOfSwipe++;
                ControlPanelPage.blockOfNews.perform(swipeUp());
            }else if (numberOfSwipe == 10){
                massen = false;
            }else {
                number++;
            }
        }
        return number;
    }






}

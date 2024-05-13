package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.FilterNewsPage;
import ru.iteco.fmhandroid.ui.Elements.NewsPage;
import ru.iteco.fmhandroid.ui.FileHelper;

public class FilterPageStep {
    public void isFilterNews () {
        Allure.step("Проверка что мы находлимся на вкладке 'Filter news' ");
        FilterNewsPage.titleOfFilterPage.check(matches(isDisplayed()));
    }

    public String category (int position) {
        Allure.step("Выбрать категорию новости");
        return FileHelper.Text.getText(FilterNewsPage.categoryField(position));
    }

    public void enterStartDate (String startDate) {
        Allure.step("Заполнить поле начальная дата");
        FilterNewsPage.startDateField.perform(replaceText(startDate));
    }

    public void enterEndDate (String endDate) {
        Allure.step("Заполнить поле конечная дата");
        FilterNewsPage.endDateField.perform(replaceText(endDate));
    }

    public void clickFilter () {
        Allure.step("Кликнуть кнопку фильтр");
        FilterNewsPage.filterButton.perform(click());
    }


    public void clickOnActiveCheckBox () {
        Allure.step("Кликнуть чек-бокс активна");
        FilterNewsPage.activeCheckBox.perform(click());
    }

    public void clickOnNotActiveCheckBox() {
        Allure.step("Кликнуть чек-бокс не активна");
        FilterNewsPage.inactiveCheckBox.perform(click());
    }

}

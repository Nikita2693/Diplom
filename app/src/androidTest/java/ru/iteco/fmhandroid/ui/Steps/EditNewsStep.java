package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Elements.EditNewsPage;

public class EditNewsStep {
    public void isEditNews () {
        Allure.step("Проверка что мы находлимся на вкладке 'Editing news' ");
        EditNewsPage.titleOfEditPage.check(matches(isDisplayed()));
    }

    public void editTitle (String title) {
        Allure.step("Изменить заголовок новости");
        EditNewsPage.editTitle.perform(replaceText(title));
    }

    public void editDescription (String description) {
        Allure.step("Изменить описание новости");
        EditNewsPage.editDescription.perform(replaceText(description));
    }

    public void editPuplicationDate (String puplicationDate) {
        Allure.step("Изменить дату публикации новости");
        EditNewsPage.editPublicationDate.perform(replaceText(puplicationDate));
    }

    public void editTimePuplication (String timePuplication) {
        Allure.step("Изменить время новости");
        EditNewsPage.editTime.perform(replaceText(timePuplication));
    }

    public void statusSwitcher () {
        Allure.step("Изменить статус новости (актианый / не активный)");
        EditNewsPage.statusSwitcher.perform(click());
    }

    public void clickSaveButton () {
        Allure.step("Кликнуть на сохранения новости");
        EditNewsPage.saveButton.perform(click());
    }

    public void clickCancel () {
        Allure.step("Кликнуть на отмену  сохранения новости");
        EditNewsPage.cancelButton.perform(click());
    }

}

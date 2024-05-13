package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.FileHelper.waitForElement;
import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Elements.FilterNewsPage;
import ru.iteco.fmhandroid.ui.Elements.NewsPage;
import ru.iteco.fmhandroid.ui.FileHelper;

public class ControlPanelSteps {


    public void isControlPanelNews () {
        Allure.step("Проверка что мы находлимся на вкладке 'Control panel' ");
        ControlPanelPage.titleOfControlPanelPage.check(matches(isDisplayed()));
    }
    public void clickEditNews(String newsTitle) {
        Allure.step("Нажатие кнопки редактирование новости");
        ControlPanelPage.editNews(newsTitle).perform(click());
    }

    public void clickCreateNews() {
        Allure.step("Нажатие кнопки создание новости");
        ControlPanelPage.createNewsButton.perform(click());
    }

    public void clickDeleteNews(String newsTitle) {
        Allure.step("Нажатие кнопки удаление новости");
        ControlPanelPage.deleteNewsButton(newsTitle).perform(click());
        NewsPage.okButton.perform(click());
    }

    public void clickDeleteNewsAndNotConfirmDeletion(String newsTitle) {
        Allure.step("Нажатие кнопки удаление новости и не подтверждения удаления");
        ControlPanelPage.deleteNewsButton(newsTitle).perform(click());
        ControlPanelPage.cancelButtonInDelete.perform(click());
    }

    public String getTitleNews ( int number) {
        Allure.step("Получения названия новости");
        return FileHelper.Text.getText(ControlPanelPage.newsItemTitle(number));
    }

    public String getDescriptionNews ( int number) {
        Allure.step("Получения описания новости");
        return FileHelper.Text.getText(ControlPanelPage.textSingNews(number));
    }

    public String checkActiveOrNotActiveStatus ( int number ) {
        Allure.step("Получения статуса новости Активна / Не активна");
        return FileHelper.Text.getText(ControlPanelPage.newsStatusActiveOrNotActive(number));
    }

    public void clickTitleNews (int number) {
        ControlPanelPage.newsItemTitle(number).perform(click());
    }

}

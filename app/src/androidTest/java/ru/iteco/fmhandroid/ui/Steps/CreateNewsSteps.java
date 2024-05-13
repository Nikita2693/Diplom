package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static ru.iteco.fmhandroid.ui.FileHelper.Rand.getCurrentTime;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.CreatingNewsPage;

public class CreateNewsSteps {

    public void createNews(String category, String title, String date, String time, String description) {
        Allure.step("Создание новости");
        CreatingNewsPage.editCategory.perform(replaceText(category));
        CreatingNewsPage.editTitle.perform(replaceText(title));
        CreatingNewsPage.editPublicationDate.perform(replaceText(date));
        CreatingNewsPage.editTime.perform(replaceText(time));
        CreatingNewsPage.editDescription.perform(replaceText(description));
        CreatingNewsPage.saveButton.perform(click());
    }
}

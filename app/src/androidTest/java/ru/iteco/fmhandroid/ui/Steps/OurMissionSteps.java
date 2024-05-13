package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.OurMissionPage;

public class OurMissionSteps {

    public void isOurMissionScreen() {
        Allure.step("Проверка что мы находлимся на вкладке 'Love is all' ");
        OurMissionPage.loveIsAll.check(matches(isDisplayed()));
    }
    public void showOrHideQuote(int number) {
        Allure.step("Развернуть/свернуть цитату");
        OurMissionPage.missionList.check(matches(isDisplayed()));
        OurMissionPage.missionList.perform(actionOnItemAtPosition(number, click()));
    }
    public void descriptionIsDisplayed(String text) {
        Allure.step("Проверка  содержания цитаты");
        OurMissionPage.quoteBody(text).check(matches(isDisplayed()));
    }
}

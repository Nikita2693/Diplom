package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.AppElements;

public class AboutAppSteps {

    public static void checkThatAboutBlockContentIsFull() {
        Allure.step("Блок О Хосписе ");
        AppElements.versionValue.check(matches(isDisplayed()));
        AppElements.privacyPolicyValue.check(matches(isDisplayed()));
        AppElements.termsOfUseValue.check(matches(isDisplayed()));
        AppElements.infoLabel.check(matches(isDisplayed()));
    }

    public static void goToPrivacyPolicy() {
        Allure.step("Переход по ссылке политики конфиденциальности");
        AppElements.privacyPolicyValue.perform(click());
    }

    public static void goToTermsOfUse() {
        Allure.step("Переход по ссылке пользовательско соглашения");
        AppElements.termsOfUseValue.perform(click());
    }
}

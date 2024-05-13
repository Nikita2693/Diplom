package ru.iteco.fmhandroid.ui.Steps;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.AboutPage;

public class AboutSteps {

    public void checkingAllElements () {
        Allure.step("Проверка элементов экрана О приложении");
        AboutPage.tradeMarkImage.check(matches(isDisplayed()));
        AboutPage.version.check(matches(isDisplayed()));
        AboutPage.versionValue.check(matches(isDisplayed()));
        AboutPage.privacyPolicy.check(matches(isDisplayed()));
        AboutPage.privacyPolicyLink.check(matches(isDisplayed()));
        AboutPage.termsOfUse.check(matches(isDisplayed()));
        AboutPage.termsOfUseLink.check(matches(isDisplayed()));
        AboutPage.companyInfo.check(matches(isDisplayed()));
        AboutPage.returnBtn.check(matches(isDisplayed()));
    }
    public void privacyPolicyLinkClickable() {
        Allure.step("Проверка кликабильности ссылки Политика конфиденциальности");
        AboutPage.privacyPolicyLink.check(matches(isClickable()));
    }

    public void clickPrivacyPolicyLink() {
        AboutPage.privacyPolicyLink.perform(click());
    }

    public void termsLinkClickable() {
        Allure.step("Проверка кликабельности ссылки Условия использования");
        AboutPage.termsOfUseLink.check(matches(isClickable()));
    }

    public void clickTermsLink() {
        AboutPage.termsOfUseLink.perform(click());
    }

    public void clickReturnBtn() {
        Allure.step("Проверка кнопки возврат");
        AboutPage.returnBtn.perform(click());
    }

}

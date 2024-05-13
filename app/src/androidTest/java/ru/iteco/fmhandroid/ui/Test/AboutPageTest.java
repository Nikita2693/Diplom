package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.Elements.AboutPage.companyInfo;
import static ru.iteco.fmhandroid.ui.Elements.AutorizationPage.loginField;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.FileHelper;
import ru.iteco.fmhandroid.ui.Steps.AboutSteps;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutPageTest {

    AboutSteps aboutSteps = new AboutSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        try {
            FileHelper.elementWaiting(withId(R.id.login_text_input_layout), 6000);
            authorizationSteps.logIn("login2", "password2");
        } catch (Exception e) {
            mainSteps.ClickAboutInMenu();
        }
    }

    @Test
    @DisplayName("Проверка отображения страницы и всех элементов  'О приложении'")
    @Description("Корректность отображения элементов")
    public void сheckAboutScreenElements() {
        aboutSteps.checkingAllElements();

    }

    @Test
    @DisplayName("Проверка кликабельности ссылок")
    @Description("Кликабельность ссылок Политики конфиденциальности и Пользовательского соглашения")
    public void checkCheckLinksAreClickable() {
        aboutSteps.privacyPolicyLinkClickable();
        aboutSteps.termsLinkClickable();
    }

    @Test
    @DisplayName("Проверка кнопки Назад")
    @Description("При нажатии на кнопку Назад происходит переход на предыдущий экран приложения")
    public void checkCheckGoBackToPreviousScreen() {
        aboutSteps.clickReturnBtn();
        authorizationSteps.checkTextAuthorization();
    }
}

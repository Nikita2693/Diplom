package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.Elements.AutorizationPage.loginField;
import static ru.iteco.fmhandroid.ui.FileHelper.waitForElement;

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

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.AutorizationPage;
import ru.iteco.fmhandroid.ui.FileHelper;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AutorizationTests {

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";
    String notValidLogin = "logginn12";
    String notValidPassword = "Passswword";
    String emptyLoginPassword = "";
    @Before
    public void sleep() throws InterruptedException {
        try {
            FileHelper.elementWaiting(withId(R.id.login_text_input_layout), 6000);
        } catch (Exception e) {
            authorizationSteps.logOut();
        }

    }


    @Test
    @DisplayName("Вход в личный кабинет с валидными данными")
    @Description("При вводе валидного логина и пароля пользователь переходит на главный экран приложения")
    public void autorizationValidTests() throws InterruptedException {
        authorizationSteps.logIn(validLogin, validPassword);
        authorizationSteps.checkTextAuthorization();
    }
    @Test
    @DisplayName("Вход в личный кабинет с  невалидными логиным и валидным паролем")
    @Description("При попытке авторизации с невалидным логином, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationNotValidLoginValidPassword () throws InterruptedException {
        authorizationSteps.logIn(notValidLogin, validPassword);
        authorizationSteps.errorPopupWindow();
    }
    @Test
    @DisplayName("Вход в личный кабинет с валидными логиным и невалидным паролем")
    @Description("При попытке авторизации с невалидным паролем, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationValidLoginNotValidPassword () throws InterruptedException {
        authorizationSteps.logIn(validLogin, notValidPassword);
        authorizationSteps.errorPopupWindow();
    }
    @Test
    @DisplayName("Вход в личный кабинет с невалидными логиным и невалидным паролем")
    @Description("При попытке авторизации с невалидным логином и паролем, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationNotValidLoginNotValidPassword () throws InterruptedException {
        authorizationSteps.logIn(notValidLogin, notValidPassword);
        authorizationSteps.errorPopupWindow();
    }
    @Test
    @DisplayName("Вход в личный кабинет с пустым логиным и валидным паролем")
    @Description("При попытке авторизации с пустым логиным и валидным паролем, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationEmptyLoginValidPassword () throws InterruptedException {
        authorizationSteps.logIn(emptyLoginPassword, validPassword);
        authorizationSteps.errorPopupWindow();
    }
    @Test
    @DisplayName("Вход в личный кабинет с валидным логиным и пустым паролем")
    @Description("При попытке авторизации с валидным логиным и пустым паролем, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationValidLoginEmptyPassword () throws InterruptedException {
        authorizationSteps.logIn(validLogin, emptyLoginPassword);
        authorizationSteps.errorPopupWindow();
    }
    @Test
    @DisplayName("Вход в личный кабинет с пустым логиным и паролем")
    @Description("При попытке авторизации с пустым логиным и паролем, пользователь не авторизуется, всплывает собщение ошибки")
    public void autorizationEmptyLoginEmptyPassword () throws InterruptedException {
        authorizationSteps.logIn(emptyLoginPassword, emptyLoginPassword);
        authorizationSteps.errorPopupWindow();
    }




}

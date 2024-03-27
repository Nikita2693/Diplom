package ru.iteco.fmhandroid.ui.tests;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;


@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTests {
    @Rule
    public ActivityTestRule<ru.iteco.fmhandroid.ui.AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    String InvalidLogin = "логин2";
    String InvalidPassword = "пароль2";

    String NotValidLogin = "login22";
    String NotValidPassword = "password22";

    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    public void shouldLogInWithValidData() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        ControlPanelSteps.logOut();
    }

    @Test
    @DisplayName("Авторизация с неккоректным логином и паролем")
    public void shouldLogInWithInValidData() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(InvalidLogin, InvalidPassword);
        ControlPanelSteps.checkButtonLogIn();
        AuthorizationSteps.checkTextAuthorization();
    }
    @Test
    @DisplayName("Авторизация с некорректным логином и корректным паролем")
    public void shouldNotValidLogInWithValidPassword() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(NotValidLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        ControlPanelSteps.logOut();
    }
    @Test
    @DisplayName("Авторизация с корректным логином и некорректным паролем")
    public void shouldValidLogInWithNotValidPassword() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, NotValidPassword);
        ControlPanelSteps.checkButtonLogIn();
        ControlPanelSteps.logOut();
    }

    @Test
    @DisplayName("Авторизация с пустыми полями логин и пароль")
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.clickSignInButton();
        AuthorizationSteps.checkTextAuthorization();
    }

    @Test
    @DisplayName("Выход из профиля")
    public void shouldLogOut() throws InterruptedException {
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.logOut();
    }
}


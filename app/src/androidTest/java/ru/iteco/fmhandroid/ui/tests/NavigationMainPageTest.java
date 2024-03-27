package ru.iteco.fmhandroid.ui.tests;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@RunWith(AllureAndroidJUnit4.class)

public class NavigationMainPageTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);
    String validLogin = "login2";
    String validPassword = "password2";

    @Test
    @DisplayName("Отображение главной страницы с блоками \"Новости\" и \"Заявки\"")
    public void allMainPageDisplay() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        Thread.sleep(5000);
        MainPageSteps.homePageDisplay();
    }

    @Test
    @DisplayName("Нажать кнопку \"свернуть/развернуть\" новости")
    public void expButtonNews() throws InterruptedException {
        Thread.sleep(5000);
        MainPageSteps.clickTheExpandNewsButton();
    }

    @Test
    @DisplayName("Нажать кнопку \"свернуть/развернуть\" заявки")
    public void expButtonClaims() throws InterruptedException {
        Thread.sleep(5000);
        MainPageSteps.clickTheExpandClaimsButton();
    }
}

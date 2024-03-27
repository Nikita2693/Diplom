package ru.iteco.fmhandroid.ui.tests;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AboutAppSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.BrowserSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@RunWith(AllureAndroidJUnit4.class)
public class AppTest {

    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(ru.iteco.fmhandroid.ui.AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";


    @Test
    @DisplayName("Информация раздела \"О приложении\"")
    public void shouldBeFullContentInAboutBlock() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        Thread.sleep(5000);
        ControlPanelSteps.goToAboutBlock();
        AboutAppSteps.checkThatAboutBlockContentIsFull();
    }

    @Test
    @DisplayName("Переход к политике конфиденциальности по ссылке")
    public void shouldGoToPrivacyPolicy() throws InterruptedException {
        Thread.sleep(5000);
        ControlPanelSteps.goToAboutBlock();
        AboutAppSteps.goToPrivacyPolicy();
        BrowserSteps.checkTheSuccessfulTransitionToPrivacyPolicy();

    }

    @Test
    @DisplayName("Переход к пользовательскому соглашению по ссылке")
    public void shouldGoToUserAgreement() throws InterruptedException {
        Thread.sleep(5000);
        ControlPanelSteps.goToAboutBlock();
        AboutAppSteps.goToTermsOfUse();
        BrowserSteps.checkTheSuccessfulTransitionToTermsOfUse();
    }
}
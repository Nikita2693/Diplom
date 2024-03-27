package ru.iteco.fmhandroid.ui.tests;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimCreationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.AppActivity;
import androidx.test.rule.ActivityTestRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


@RunWith(AllureAndroidJUnit4.class)
public class ClaimCreationTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    @Test
    @DisplayName("Создание заявки с валидными значениями")
    public void allCreatingAnApplicationWithValidValues() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        Thread.sleep(5000);
        ClaimCreationSteps.creatingAnApplicationAllFieldsAreFilledIn();
    }

    @Test
    @DisplayName("Создание заявки с пустыми полями")
    public void creatingAnApplicationWithEmptyFields() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.creatingAnApplicationNotAllFieldsAreFilledIn();
    }

    @Test
    @DisplayName("Отмена создания заявки")
    public void cancelingCreatingAnApp() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.cancelingTheCreationOfTheApplication();
    }

    @Test
    @DisplayName("Отмена ОТМЕНЫ создания заявки")
    public void cancelingCancelCreatingAnApp() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.cancelingCancelTheCreationOfTheApplication();
    }

    @Test
    @DisplayName("Изменение статуса заявки на \"Открыта\"")
    public void changingTheStatusToOpen() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.statusOpen();
    }

    @Test
    @DisplayName("Изменение статуса заявки на \"В работе\"")
    public void changingTheStatusToProgress() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.statusInProgress();
    }

    @Test
    @DisplayName("Добавление комментария к заявке")
    public void commentAdd() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.commentToTheApplication();
    }

    @Test
    @DisplayName("Редактирование комментария к заявке")
    public void commentEdit() throws InterruptedException {
        Thread.sleep(5000);
        ClaimCreationSteps.commentEditToTheApplication();
    }
}

package ru.iteco.fmhandroid.ui.tests;

import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsFilteringSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@RunWith(AllureAndroidJUnit4.class)

public class ClaimsFilteringTests {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    @Test
    @DisplayName("Фильтрация заявки по статусу \"Открыта\"")
    public void authStatusFilteringOpen() throws InterruptedException {
        Thread.sleep(5000);
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkButtonLogIn();
        Thread.sleep(5000);
        ClaimsFilteringSteps.filterStatusOpen();
    }

    @Test
    @DisplayName("Фильтрация заявки по статусу \"В работе\"")
    public void statusFilteringInProgress() throws InterruptedException {
        Thread.sleep(5000);
        ClaimsFilteringSteps.filterStatusInProgress();
    }

    @Test
    @DisplayName("Фильтрация заявки по статусу \"Выполнена\"")
    public void statusFilteringCompleted() throws InterruptedException {
        Thread.sleep(5000);
        ClaimsFilteringSteps.filterStatusCompleted();
    }

    @Test
    @DisplayName("Фильтрация заявки по статусу \"Отмененные\"")
    public void statusFilteringCanceled() throws InterruptedException {
        Thread.sleep(5000);
        ClaimsFilteringSteps.filterStatusCanceled();
    }

    @Test
    @DisplayName("Фильтрация не выбрана")
    public void statusFilteringNotSelected() throws InterruptedException {
        Thread.sleep(5000);
        ClaimsFilteringSteps.filterStatusNotSelected();
    }
}

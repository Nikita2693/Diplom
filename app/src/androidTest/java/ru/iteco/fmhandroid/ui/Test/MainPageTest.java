package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.Elements.AboutPage.*;
import static ru.iteco.fmhandroid.ui.Elements.AutorizationPage.authorizationButton;
import static ru.iteco.fmhandroid.ui.Elements.MainPage.*;
import static ru.iteco.fmhandroid.ui.Elements.NewsPage.*;
import static ru.iteco.fmhandroid.ui.Elements.OurMissionPage.*;
import static ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps.*;

import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.appcompat.widget.ThemedSpinnerAdapter;
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
import kotlin.io.TextStreamsKt;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.MainPage;
import ru.iteco.fmhandroid.ui.FileHelper;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;
import androidx.test.espresso.NoMatchingViewException;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainPageTest {
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
            authorizationSteps.checkTextAuthorization();
        }

    }

    @Test
    @DisplayName("Проверка списка вкладок кнопки меню")
    @Description("При нажатии на кнопку меню в выпадающем списке есть разделы Главная, Новости, О приложении")
    public void menuElementsShouldBeVisible() throws InterruptedException {
        mainSteps.clickMainMenu();
        mainOfMenu.check(matches(isDisplayed()));
        newsOfMenu.check(matches(isDisplayed()));
        aboutOfMenu.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход по вкладкам с помощью кнопки меню")
    @Description("При нажатии на название экрана в выпадающем списке кнопки меню, пользователь переходит на соответствующую страницу приложения")
    public void checkTransitionToScreensMenuBtn() throws InterruptedException {
        mainSteps.ClickNewsInMenu();
        titleOfNewsBlock.check(matches(isDisplayed()));
        mainSteps.ClickMainInMenu();
        newsOfMenu.check(matches(isDisplayed()));
        mainSteps.ClickAboutInMenu();
        companyInfo.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход на страницу Наша миссия")
    @Description("При нажатии на кнопку в виде бабочки пользователь переходит на страницу Наша миссия")
    public void checkTransitionToOurMissionScreen() throws InterruptedException {
        mainSteps.ClickOurMission();
        loveIsAll.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Выход авторизованного пользователя")
    @Description("При нажатии на кнопку Авторизация с рисунком миниатюры человека и дальнейшее нажатие 'Log out', происходит разлогирование")
    public void checkLogOut() throws InterruptedException {
        authorizationSteps.logOut();
    }

    @Test
    @DisplayName("Кликабельность кнопки развернуть/свернуть список новостей")
    @Description("При нажатии на блок новостей новости сворачиваются, при повторном нажатии - разворачиваются")
    public void checkShowOrHideNewsBlock() throws InterruptedException {
        mainSteps.ExpandAllNews();
        allNewsBtn.check(matches(not(isDisplayed())));
        mainSteps.ExpandAllNews();
        allNewsBtn.check(matches(isDisplayed()));
    }

}

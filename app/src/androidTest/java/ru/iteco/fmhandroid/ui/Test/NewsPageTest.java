package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static ru.iteco.fmhandroid.ui.FileHelper.Rand.getCurrentDate;
import static ru.iteco.fmhandroid.ui.FileHelper.Rand.getCurrentTime;
import static ru.iteco.fmhandroid.ui.FileHelper.Rand.random;
import static ru.iteco.fmhandroid.ui.FileHelper.withIndex;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Elements.CreatingNewsPage;
import ru.iteco.fmhandroid.ui.Elements.FilterNewsPage;
import ru.iteco.fmhandroid.ui.Elements.NewsPage;
import ru.iteco.fmhandroid.ui.FileHelper;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.CreateNewsSteps;
import ru.iteco.fmhandroid.ui.Steps.EditNewsStep;
import ru.iteco.fmhandroid.ui.Steps.FilterPageStep;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;
import ru.iteco.fmhandroid.ui.Steps.NewsStep;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTest {

    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    NewsStep newsStep = new NewsStep();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreateNewsSteps createNewsSteps = new CreateNewsSteps();
    EditNewsStep editNewsStep = new EditNewsStep();
    FilterPageStep filterPageStep = new FilterPageStep();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        try {
            FileHelper.elementWaiting(withId(R.id.login_text_input_layout), 6000);
            authorizationSteps.logIn("login2", "password2");
        } catch (Exception e) {
            mainSteps.ClickNewsInMenu();
        }
    }

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    @DisplayName("Проверка отображения страницы новости")
    @io.qameta.allure.kotlin.Description("Корректность отображения элемента")
    public void checkCheckNewsScreenElements() {
        newsStep.isNewsScreen();
    }


    @Test // может падать
    @DisplayName("Развернуть любую новость")
    @io.qameta.allure.kotlin.Description("При нажатии на новость разворачивается ее содержание")
    public void checkShowNewsContent() {
        int number = 1;
        newsStep.openNews(number);
        newsStep.newsContentIsDisplayed(number);
    }


    @Test
    @DisplayName("Проверка кнопки сортировки новостей")
    @io.qameta.allure.kotlin.Description("При нажатии на кнопку сортировки меняется порядок отображения новостей по дате создания новости")
    public void checkSortNews() throws InterruptedException {
        int number = 0;
        String firstNewsTitle = FileHelper.Text.getText(NewsPage.newsTitle(number));
        newsStep.clickSortButton();
        newsStep.clickSortButton();
        NewsPage.allNewsList.perform(swipeDown());
        //Thread.sleep(1000);
        String firstNewsTitleAfterSecondSorting = FileHelper.Text.getText(NewsPage.newsTitle(number));
        assertEquals(firstNewsTitle, firstNewsTitleAfterSecondSorting);
    }


    @Test
    @DisplayName("Создать новость на латинице")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными на латинице создается новость проверяется правильность ее создания")
    public void checkCreateNewsLatin() throws InterruptedException {
        int position = 0;
        int number = random(6, 7, 8, 9, 10);
        String newsTitleLatin = "News Title" + number;
        String newsDescriptionLatin = "News Description" + " " + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitleLatin, dateOfPublication, getCurrentTime(), newsDescriptionLatin);
        position = newsStep.findNews(newsTitleLatin, position);
        String textOfTitleNews = FileHelper.Text.getText(NewsPage.newsTitle(position));
        assertEquals(newsTitleLatin, textOfTitleNews);
        String textOfDescriptionNews = FileHelper.Text.getText(NewsPage.newsBody(position));
        assertEquals(newsDescriptionLatin, textOfDescriptionNews);
    }

    @Test
    @DisplayName("Создать новость на кириллице")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными на кириллице создается новость проверяется правильность ее создания")
    public void checkCreateNewsCyrillic() throws InterruptedException {
        int position = 0;
        int number = random(1, 2, 3, 4, 5);
        String newsTitleCyrillic = "Заголовок новости" + number;
        String newsDescriptionCyrillic = "Описание новости" + " " + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitleCyrillic, dateOfPublication, getCurrentTime(), newsDescriptionCyrillic);
        position = newsStep.findNews(newsTitleCyrillic, position);
        String textOfTitleNews = FileHelper.Text.getText(NewsPage.newsTitle(position));
        assertEquals(newsTitleCyrillic, textOfTitleNews);
        String textOfDescriptionNews = FileHelper.Text.getText(NewsPage.newsBody(position));
        assertEquals(newsDescriptionCyrillic, textOfDescriptionNews);
    }

    @Test
    @DisplayName("Создать новость с пустой темой")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными, кроме темы - поле остается пустым, всплывает сообщение об ошибке")
    public void checkCreateNewsEmptyTitle() throws InterruptedException {
        int number = random(1, 2, 3, 4, 5);
        String newsTitleEmpty = "";
        String newsDescriptionCyrillic = "Описание новости" + " " + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText("Объявление"));
        CreatingNewsPage.editTitle.perform(replaceText(newsTitleEmpty));
        CreatingNewsPage.editPublicationDate.perform(replaceText(dateOfPublication));
        CreatingNewsPage.editTime.perform(replaceText(getCurrentTime()));
        CreatingNewsPage.editDescription.perform(replaceText(newsDescriptionCyrillic));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                    .inRoot(withDecorView(not(decorView)))
                    .check(matches(isDisplayed()));


    }

    @Test
    @DisplayName("Создать новость с пустой категорией")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными, кроме категории - поле остается пустым, всплывает сообщение об ошибке")
    public void checkCreateNewsEmptyCategory() throws InterruptedException {
        int number = random(1, 2, 3, 4, 5);
        String newsTitleCyrillic = "Заголовок новости" + number;
        String newsDescriptionCyrillic = "Описание новости" + " " + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText(""));
        CreatingNewsPage.editTitle.perform(replaceText(newsTitleCyrillic));
        CreatingNewsPage.editPublicationDate.perform(replaceText(dateOfPublication));
        CreatingNewsPage.editTime.perform(replaceText(getCurrentTime()));
        CreatingNewsPage.editDescription.perform(replaceText(newsDescriptionCyrillic));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость с пустой датой")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными, кроме даты - поле остается пустым, всплывает сообщение об ошибке")
    public void checkCreateNewsEmptyDate() throws InterruptedException {
        int number = random(1, 2, 3, 4, 5);
        String newsTitleCyrillic = "Заголовок новости" + number;
        String newsDescriptionCyrillic = "Описание новости" + " " + number;
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText("Объявление"));
        CreatingNewsPage.editTitle.perform(replaceText(newsTitleCyrillic));
        CreatingNewsPage.editPublicationDate.perform(replaceText(""));
        CreatingNewsPage.editTime.perform(replaceText(getCurrentTime()));
        CreatingNewsPage.editDescription.perform(replaceText(newsDescriptionCyrillic));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость с пустой полем время")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными, кроме поле времени - поле остается пустым, всплывает сообщение об ошибке")
    public void checkCreateNewsEmptyTime() throws InterruptedException {
        int number = random(1, 2, 3, 4, 5);
        String newsTitleCyrillic = "Заголовок новости" + number;
        String newsDescriptionCyrillic = "Описание новости" + " " + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText("Объявление"));
        CreatingNewsPage.editTitle.perform(replaceText(newsTitleCyrillic));
        CreatingNewsPage.editPublicationDate.perform(replaceText(dateOfPublication));
        CreatingNewsPage.editTime.perform(replaceText(""));
        CreatingNewsPage.editDescription.perform(replaceText(newsDescriptionCyrillic));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость с пустой полем описание")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными, кроме поле описание - поле остается пустым, всплывает сообщение об ошибке")
    public void checkCreateNewsEmptyDescription() throws InterruptedException {
        int number = random(1, 2, 3, 4, 5);
        String newsTitleCyrillic = "Заголовок новости" + number;
        String newsDescriptionCyrillic = "";
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText("Объявление"));
        CreatingNewsPage.editTitle.perform(replaceText(newsTitleCyrillic));
        CreatingNewsPage.editPublicationDate.perform(replaceText(dateOfPublication));
        CreatingNewsPage.editTime.perform(replaceText(getCurrentTime()));
        CreatingNewsPage.editDescription.perform(replaceText(newsDescriptionCyrillic));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость спецсимволами")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей спецсиволами, кроме поле категория - поле заполняется валидным значение, создается новость проверяется правильность ее создания")
    public void checkCreateNewsEmptySpecialSymbols() throws InterruptedException {
        int position = 0;
        int number = random(1, 2, 3, 4, 5);
        String newsTitle = "!@#$%^&*()_+" + number;
        String newsDescription = "!@#$%^&*()_+" + number;
        String dateOfPublication = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitle, dateOfPublication, getCurrentTime(), newsDescription);
        position = newsStep.findNews(newsTitle, position);
        String textOfTitleNews = FileHelper.Text.getText(NewsPage.newsTitle(position));
        assertEquals(newsTitle, textOfTitleNews);
        String textOfDescriptionNews = FileHelper.Text.getText(NewsPage.newsBody(position));
        assertEquals(newsDescription, textOfDescriptionNews);

    }

    @Test
    @DisplayName("Создать новости с пустыми полями")
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей оставляем их пустыми, новость не создается")
    public void checkCreateNewsEmptyAllField() throws InterruptedException {
        String newsEmptyAllField = "";
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        CreatingNewsPage.editCategory.perform(replaceText(newsEmptyAllField));
        CreatingNewsPage.editTitle.perform(replaceText(newsEmptyAllField));
        CreatingNewsPage.editPublicationDate.perform(replaceText(newsEmptyAllField));
        CreatingNewsPage.editTime.perform(replaceText(newsEmptyAllField));
        CreatingNewsPage.editDescription.perform(replaceText(newsEmptyAllField));
        CreatingNewsPage.saveButton.perform(click());
        onView(withText("Fill empty fields"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость с валидными данными, после проверить соответствие данных в созданной новости")   //тест падает - не совподает дата создания новости
    @io.qameta.allure.kotlin.Description("При заполнении текстовых полей валидными данными на латинице создается новость проверяется правильность ее создания")
    public void checkCreateNewsLatin1() throws InterruptedException {
        int position = 0;
        int number = random(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String newsTitle = "News Title" + number;
        String newsDescription = "News Description" + " " + number;
        String dateOfPublication = getCurrentDate();
        String dateOfCreation = getCurrentDate();
        newsStep.clickEditButtontn();
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitle, dateOfPublication, getCurrentTime(), newsDescription);
        position = newsStep.findNews(newsTitle, position);
        String textOfTitleNews = FileHelper.Text.getText(NewsPage.newsTitle(position));
        assertEquals(newsTitle, textOfTitleNews);
        String textOfDescriptionNews = FileHelper.Text.getText(NewsPage.newsBody(position));
        assertEquals(newsDescription, textOfDescriptionNews);
        String getDateOfPublication = FileHelper.Text.getText(ControlPanelPage.publicationDate(position));
        assertEquals(dateOfPublication, getDateOfPublication);
        String getDateOfCreation = FileHelper.Text.getText(ControlPanelPage.creationDate(position));
        assertEquals(dateOfCreation, getDateOfCreation);
    }

    @Test
    @DisplayName("Проверка прерывания создания новости с помошью кнопки 'Cancel'")
    @io.qameta.allure.kotlin.Description("При создании новости нажимаем 'Cancel', новость не создаеться")
    public void checkCreateNewsAndCancelCreate() throws InterruptedException {
        NewsPage.editButton.perform(click());
        ControlPanelPage.createNewsButton.perform(click());
        newsStep.clickCancel();
        NewsPage.okButton.perform(click());
        newsStep.isControlPanelScreen();

    }

    @Test
    @DisplayName("Фильтрация по дате на странице новостей")
    @io.qameta.allure.kotlin.Description("Отображаются только те новости, которые были созданы в указанный промежуток времени")
    public void checkDateInFilter() {
        String newsDate = getCurrentDate();
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        filterPageStep.clickFilter();
        String getDateOfPublication = FileHelper.Text.getText(NewsPage.newsData(0));
        assertEquals(newsDate, getDateOfPublication);
    }

    @Test
    @DisplayName("Нет новостей, соответствующих критериям поиска")
    @io.qameta.allure.kotlin.Description("При отсуствии новостей, удовлетворяющим критериям поиска (дате создания), на экране видна предупреждающая надпись")
    public void checkdShowNothingToShowScreen() {
        String newsDate = "17.10.1958";
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        filterPageStep.clickFilter();
        FilterNewsPage.titleNewsEmptySearch.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Отмена фильтрации")
    @io.qameta.allure.kotlin.Description("Выход из экрана фильтра без фильтрации новостей")
    public void checkCancelFilter() {
        String newsDate = getCurrentDate();
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        newsStep.clickCancel();
        newsStep.isNewsScreen();
    }

    @Test
    @DisplayName("Проверка кнопки сортировки новостей в 'Control panel'")
    @io.qameta.allure.kotlin.Description("При нажатии на кнопку сортировки меняется порядок отображения новостей по дате создания новости")
    public void checkSortNewsInControlPanel () throws InterruptedException {
        int number = 0;
        newsStep.clickEditButtontn();
        String firstNewsTitle = FileHelper.Text.getText(NewsPage.newsTitle(number));
        newsStep.clickSortButton();
        newsStep.clickSortButton();
        ControlPanelPage.newsItemTitle(0).perform(swipeDown());
        //Thread.sleep(1000);
        String firstNewsTitleAfterSecondSorting = FileHelper.Text.getText(NewsPage.newsTitle(number));
        assertEquals(firstNewsTitle, firstNewsTitleAfterSecondSorting);
    }

    @Test
    @DisplayName("Фильтрация по дате на странице 'Control panel'")
    @io.qameta.allure.kotlin.Description("Отображаются только те новости, которые были опубликованы в указанный промежуток времени")
    public void checkDateInFilterControlPanel() {
        String newsDate = getCurrentDate();
        newsStep.clickEditButtontn();
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        filterPageStep.clickFilter();
        String getDateOfPublication = FileHelper.Text.getText(ControlPanelPage.publicationDate(0));
        assertEquals(newsDate, getDateOfPublication);
    }

    @Test
    @DisplayName("Нет новостей, соответствующих критериям поиска на странице 'Control panel'")
    @io.qameta.allure.kotlin.Description("При отсуствии новостей, удовлетворяющим критериям поиска (дате создания), на экране видна предупреждающая надпись")
    public void checkdShowNothingToShowScreenInControlPanel() {
        String newsDate = "17.10.1958";
        newsStep.clickEditButtontn();
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        filterPageStep.clickFilter();
        FilterNewsPage.titleNewsEmptySearchInControlPanel.check(matches(isDisplayed()));
    }


    @Test
    @DisplayName("Фильтрация новостей по статусу 'Не активна' на странице 'Control panel'")
    @io.qameta.allure.kotlin.Description("При фильтре новостей по статусу 'Не активна' в списке новостей отображаются только новости с этим статусом")
    public void checkdNotActiveNewsInControlPanel(){
        int number = 0;
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        String titleNews = controlPanelSteps.getTitleNews(number);
        String status = controlPanelSteps.checkActiveOrNotActiveStatus(number);
        if (status.equals("Active")) {
            controlPanelSteps.clickEditNews(titleNews);
            editNewsStep.isEditNews();
            editNewsStep.statusSwitcher();
            editNewsStep.clickSaveButton();
            newsStep.openFilter();
        }else {
            newsStep.openFilter();
        }
        filterPageStep.isFilterNews();
        filterPageStep.clickOnActiveCheckBox();
        filterPageStep.clickFilter();
        String titleNewsAfterFilter = controlPanelSteps.getTitleNews(number);
        assertEquals(titleNews, titleNewsAfterFilter);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу 'Активна' на странице 'Control panel'")
    @io.qameta.allure.kotlin.Description("При фильтре новостей по статусу 'Активна' в списке новостей отображаются только новости с этим статусом")
    public void checkdActiveNewsInControlPanel(){
        int number = 0;
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        String titleNews = controlPanelSteps.getTitleNews(number);
        String status = controlPanelSteps.checkActiveOrNotActiveStatus(number);
        if (status.equals("Not active")) {
            controlPanelSteps.clickEditNews(titleNews);
            editNewsStep.isEditNews();
            editNewsStep.statusSwitcher();
            editNewsStep.clickSaveButton();
            newsStep.openFilter();
        }else {
            newsStep.openFilter();
        }
        filterPageStep.isFilterNews();
        filterPageStep.clickOnNotActiveCheckBox();
        filterPageStep.clickFilter();
        String titleNewsAfterFilter = controlPanelSteps.getTitleNews(number);
        assertEquals(titleNews, titleNewsAfterFilter);
    }

    @Test
    @DisplayName("Отмена фильтрации на странице 'Control panel'")
    @io.qameta.allure.kotlin.Description("Выход из экрана фильтра без фильтрации новостей на странице 'Control panel'")
    public void checkCancelFilterInControlPanel() {
        String newsDate = getCurrentDate();
        newsStep.clickEditButtontn();
        newsStep.openFilter();
        filterPageStep.isFilterNews();
        filterPageStep.enterStartDate(newsDate);
        filterPageStep.enterEndDate(newsDate);
        newsStep.clickCancel();
        controlPanelSteps.isControlPanelNews();
    }

    @Test
    @DisplayName("Удаление новости")
    @io.qameta.allure.kotlin.Description("При нажатии и подтверждении удаления новость удаляется")
    public void checkDeleteNews() {
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        int number = random(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String newsTitle1 = "News Title " + number;
        String newsDescription1 = "News Description " + number;
        String newsTitle2 = newsTitle1 + number;
        String newsDescription2 = newsDescription1 + number;
        String dateOfPublication = getCurrentDate();
        // создаем 2 новости (на случай если новостей нет вообще)
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitle1, dateOfPublication, getCurrentTime(), newsDescription1);
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitle2, dateOfPublication, getCurrentTime(), newsDescription2);
        String newsTitleInCreate1 = FileHelper.Text.getText(ControlPanelPage.newsItemTitle(0));
        String newsTitleInCreate2 = FileHelper.Text.getText(ControlPanelPage.newsItemTitle(1));
        //удаляем 1 новость
        controlPanelSteps.clickDeleteNews(newsTitleInCreate1);
        String newsTitleAfterDelete = FileHelper.Text.getText(ControlPanelPage.newsItemTitle(0));
        assertEquals(newsTitleAfterDelete, newsTitleInCreate2);
        controlPanelSteps.clickDeleteNews(newsTitleAfterDelete);
    }

    @Test
    @DisplayName("Отмена удаление новости")
    @io.qameta.allure.kotlin.Description("При нажатии на удаление новости, не подтверждаем удаление. Новость остается")
    public void checkCancelDeleteNews() {
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        int number = random(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String newsTitle = "News Title " + number;
        String newsDescription = "News Description " + number;
        String dateOfPublication = getCurrentDate();
        controlPanelSteps.clickCreateNews();
        createNewsSteps.createNews("Объявление", newsTitle, dateOfPublication, getCurrentTime(), newsDescription);
        String newsTitleInCreate = FileHelper.Text.getText(ControlPanelPage.newsItemTitle(0));
        controlPanelSteps.clickDeleteNewsAndNotConfirmDeletion(newsTitleInCreate);
        String newsTitleAfterClickDelete = FileHelper.Text.getText(ControlPanelPage.newsItemTitle(0));
        assertEquals(newsTitleAfterClickDelete, newsTitleInCreate);
        controlPanelSteps.clickDeleteNews(newsTitleAfterClickDelete);
    }

    @Test
    @DisplayName("Редактирование новости")
    @io.qameta.allure.kotlin.Description("При нажатии на редактирование новости и изменение данных, новость отображается с новыми данными")
    public void checkEditNewsTitleDescription() {
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        int number = random(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String newsTitle = controlPanelSteps.getTitleNews(0);
        String newsTitleEdit = newsTitle + number;
        String newsDescription = controlPanelSteps.getDescriptionNews(0);
        String newsDescriptionEdit = newsDescription + number;
        controlPanelSteps.clickEditNews(newsTitle);
        editNewsStep.isEditNews();
        editNewsStep.editTitle(newsTitleEdit);
        editNewsStep.editDescription(newsDescriptionEdit);
        editNewsStep.clickSaveButton();
        String newsTitleInEdit = controlPanelSteps.getTitleNews(0);
        assertEquals(newsTitleInEdit, newsTitleEdit);
        String newsDescriptionInEdit = controlPanelSteps.getDescriptionNews(0);
        assertEquals(newsDescriptionInEdit, newsDescriptionEdit);
    }

    @Test
    @DisplayName("Кликабельность переключателя Активна/Не активна")
    @io.qameta.allure.kotlin.Description("При нажатии на редактирование можно изменить статус новости с Активна на НЕ активна и обратно. Новость отображается с новым статусом")
    public void checkEditNewsStatus() throws InterruptedException  {
        int number = 0;
        String statusActive = "Active";
        String statusNotActive = "Not active";
        newsStep.clickEditButtontn();
        controlPanelSteps.isControlPanelNews();
        String titleNews = controlPanelSteps.getTitleNews(number);
        String status1 = controlPanelSteps.checkActiveOrNotActiveStatus(number);
        if (status1.equals(statusActive)) {
            controlPanelSteps.clickEditNews(titleNews);
            editNewsStep.isEditNews();
            editNewsStep.statusSwitcher();
            editNewsStep.clickSaveButton();
            String statusInEdit1 = controlPanelSteps.checkActiveOrNotActiveStatus(number);
            assertEquals(statusInEdit1, statusNotActive);
        }
        controlPanelSteps.clickEditNews(titleNews);
        editNewsStep.isEditNews();
        editNewsStep.statusSwitcher();
        editNewsStep.clickSaveButton();
        String statusInEdit2 = controlPanelSteps.checkActiveOrNotActiveStatus(number);
        assertEquals(statusInEdit2, statusActive);
        controlPanelSteps.clickEditNews(titleNews);
        editNewsStep.isEditNews();
        editNewsStep.statusSwitcher();
        editNewsStep.clickSaveButton();
        String statusInEdit3 = controlPanelSteps.checkActiveOrNotActiveStatus(number);
        assertEquals(statusInEdit3, statusNotActive);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

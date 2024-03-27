package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

public class BrowserSteps {

    //Проверка перехода на сайт с политикой конфиденциальности
    public static void checkTheSuccessfulTransitionToPrivacyPolicy() {
        intended(allOf(
                hasData("https://vhospice.org/#/privacy-policy"),
                hasAction(Intent.ACTION_VIEW)
        ));
    }

    //Проверка перехода на сайт с пользовательским соглашением
    public static void checkTheSuccessfulTransitionToTermsOfUse() {
        intended(allOf(
                hasData("https://vhospice.org/#/terms-of-use"),
                hasAction(Intent.ACTION_VIEW)
        ));
    }

}

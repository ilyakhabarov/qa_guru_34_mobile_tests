package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android_local")
public class WikiOnboardingLocalTest extends TestBase {

    @Test
    void completeOnboardingScreens() {
        step("Проверка первой страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Свободная энциклопедия"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
        });

        step("Проверка второй страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Новые способы исследований"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка третьей страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Списки для чтения с синхронизацией"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка четвертой страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Данные и конфиденциальность"));
        });
    }
}
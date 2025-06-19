package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android_browserstack")
public class BrowserstackOnboardingTest extends TestBase {

    @Test
    void completeOnboardingScreens() {
        step("Проверка первой страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
        });

        step("Проверка второй страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка третьей страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка четвертой страницы", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Data & Privacy"));
        });
    }
}
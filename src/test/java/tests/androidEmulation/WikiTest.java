package tests.androidEmulation;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import tests.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("emulation")
public class WikiTest extends TestBase {

    @Test
    void successfulOpenOnBoardingScreenTest() {
        step("Проверка первого экрана", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
        });

        step("Проверка второго экрана", () -> {
            $(By.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(text("Dive Down the Wikipedia"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка третьего экрана", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверка четвертого экрана", () -> {
            $(By.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Data & Privacy"));
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"))
                    .shouldHave(text("Get started"));
        });
    }
}
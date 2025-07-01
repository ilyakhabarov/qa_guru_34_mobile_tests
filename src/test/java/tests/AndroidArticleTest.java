package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Tag("android_browserstack")
public class AndroidArticleTest extends TestBase {

    @Test
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка результатов поиска статей по ключевому слову")
    void openSelenideTest() {
        step("Вводим в поиске", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenide");
        });

        step("Проверяем результаты поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }

    @Test
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка отрытие статьи через поиск")
    void openArticleTest() {

        step("Ввести ключевое слово в поиск", () -> {
            back();
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Github");
        });
        step("Открыть первую статью в результатах поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .first().click());
        step("Закрыть поп-ап предложения игр", () ->
                $(id("org.wikipedia.alpha:id/closeButton")).click());
        step("Проверить открытую статью", () -> {
            $(xpath("//android.view.View[@resource-id='pcs']")).shouldBe(visible);
            $(xpath("//android.view.View[@text='GitHub']")).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка стартовых экранов приложения")
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    void verifyGettingStartedScreensTest() {

        step("Проверить первый стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("The Free Encyclopedia")));
        step("Открыть второй стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить второй стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("New ways to explore")));
        step("Открыть третий стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить третий стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Reading lists with sync")));
        step("Открыть четвертый стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить четвертый стартовый экран приложения", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Data & Privacy")));
        step("Нажать на кнопку 'Get started'", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click());
        step("Проверить отображение главной страницы приложения", () ->
                $(id("org.wikipedia.alpha:id/feed_view")).shouldBe(visible));
    }

}
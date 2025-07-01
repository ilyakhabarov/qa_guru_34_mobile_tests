package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Tag("android_browserstack")
public class SelenideSearchArticleTests extends TestBase {

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

    @Disabled
    @Test
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка открытия статьи из результатов поиска")
    void openArticleFromIncorrectSearchTest() {
        step("Вводим в поиске ключевое слово", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenide");
        });

        step("Кликаем на первую статью в результатах", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))

                        .first()
                        .click());

        step("Проверяем, что статья загрузилась", () -> {
            $(id("org.wikipedia.alpha:id/view_page_title_text"))
                    .shouldBe(visible, Duration.ofSeconds(10));
        });
    }

    @Disabled
    @Test
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка результатов поиска статей по ключевому слову")
    void emptySearchResultsTest() {
        step("Вводим несуществующий запрос", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("zxcvbnm12345");
        });

        step("Проверяем, что результатов нет", () ->
                $(id("org.wikipedia.alpha:id/search_empty_message"))
                        .shouldHave(text("No results found")));
    }
}
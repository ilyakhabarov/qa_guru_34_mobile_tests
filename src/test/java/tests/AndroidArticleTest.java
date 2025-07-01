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
    @DisplayName("Проверка открытия статьи из результатов поиска")
    void openArticleFromIncorrectSearchTest() {
        step("Вводим в поиске ключевое слово", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("JUnit");
        });

        step("Кликаем на первую статью в результатах", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .first()
                        .click());

        step("Проверяем, что статья загрузилась", () ->
                $(id("org.wikipedia.alpha:id/view_page_title_text"))
                        .shouldBe(visible));
    }

    @Test
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка открытия статьи из результатов поиска")
    void openArticleFromSearchTest() {
        step("Вводим в поиске ключевое слово", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("JUnit");
        });

        step("Кликаем на первую статью в результатах", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .first()
                        .click());

        step("Проверяем, что статья загрузилась", () ->
                $(id("org.wikipedia.alpha:id/view_page_title_text"))
                        .shouldBe(visible));
    }

}
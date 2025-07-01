package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
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
            back();
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
    void openArticleFromSearchResultsTest() {
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
    @Owner("Ilya Khabarov")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка пустых результатов поиска")
    void emptySearchResultsTest() {
        step("Ввести ключевое слово в поиск", () -> {
            back();
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("zxcqwe123");
        });

        step("Проверяем, что результатов нет", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(size(0)));
    }
}
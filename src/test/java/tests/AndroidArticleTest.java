package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Tag("android_browserstack")
public class AndroidArticleTest extends TestBase {

    @BeforeAll
    static void setup() {
        System.setProperty("platform", "android");
        System.setProperty("app", "bs://sample.app");
    }

    @Test
    void openSelenideTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenide");
        });

        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }
}
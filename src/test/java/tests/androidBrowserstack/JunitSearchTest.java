package tests.androidBrowserstack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android_browserstack")
public class JunitSearchTest extends TestBase {

    @BeforeAll
    static void setup() {
        System.setProperty("platform", "android");
    }

    @Test
    void successfulSearchTestJUnit() {
        step("Вводим слово 'JUnit5' в поиск Wikipedia", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("JUnit5");
        });

        step("Проверяем, что результаты поиска присутствуют", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}

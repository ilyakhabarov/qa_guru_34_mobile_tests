package tests.iosBrowserstack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;

@Tag("ios_browserstack")
public class JunitSearchTest extends TestBase {

    @BeforeAll
    static void setup() {
        System.setProperty("platform", "ios");
    }

    @Test
    void successfulSearchTestJUnit() {
        step("Вводим слово 'JUnit5' в поиск Wikipedia", () -> {
            $(accessibilityId("Text Button")).click();
            $(accessibilityId("Text Input")).sendKeys("JUnit5" + "\n");
        });

        step("Проверяем, что результаты поиска присутствуют", () ->
                $$(accessibilityId("Text Output"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}

package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class);

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        // Обязательные параметры для BrowserStack
        options.setCapability("browserstack.user", config.user());
        options.setCapability("browserstack.key", config.key());
        options.setCapability("app", config.app());

        // Параметры для Android
        options.setCapability("deviceName", config.androidDevice());
        options.setCapability("platformVersion", config.androidOsVersion());
        options.setCapability("platformName", "android");

        // Доп. параметры (необязательно)
        options.setCapability("project", "First Java Project");
        options.setCapability("build", "browserstack-build-1");
        options.setCapability("name", "first_test");

        try {
            return new AndroidDriver(new URL(config.url()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Browserstack URL", e);
        }
    }
}
package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {

    private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class);

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        String platform = System.getProperty("platform", "android").toLowerCase();

        // Общие настройки для BrowserStack (W3C-совместимый формат)
        Map<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", config.user());
        browserstackOptions.put("accessKey", config.key());
        browserstackOptions.put("projectName", "First Java Project");
        browserstackOptions.put("buildName", "browserstack-build-1");
        browserstackOptions.put("sessionName", "first_test");

        // Платформо-зависимые настройки
        if (platform.equals("ios")) {
            mutableCapabilities.setCapability("platformName", "iOS");
            browserstackOptions.put("deviceName", config.iosDevice());
            browserstackOptions.put("platformVersion", config.iosOsVersion());
        } else { // Android (по умолчанию)
            mutableCapabilities.setCapability("platformName", "Android");
            browserstackOptions.put("deviceName", config.androidDevice());
            browserstackOptions.put("platformVersion", config.androidOsVersion());
        }

        // App URL (для BrowserStack)
        browserstackOptions.put("app", config.app());

        // Упаковываем все в W3C-совместимый формат
        mutableCapabilities.setCapability("bstack:options", browserstackOptions);

        try {
            return new RemoteWebDriver(new URL(config.url()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Browserstack URL", e);
        }
    }
}
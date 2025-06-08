package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class);

    @NotNull
    @Override
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", config.user());
        caps.setCapability("browserstack.key", config.key());

        String platform = System.getProperty("platform");

        if (platform.equalsIgnoreCase("ios")) {
            caps.setCapability("device", config.iosDevice());
            caps.setCapability("os_version", config.iosOsVersion());
            caps.setCapability("app", config.app());
        } else {
            caps.setCapability("device", config.androidDevice());
            caps.setCapability("os_version", config.androidOsVersion());
            caps.setCapability("app", config.app());
        }

        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        try {
            return new RemoteWebDriver(new URL(config.url()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Browserstack URL", e);
        }
    }
}
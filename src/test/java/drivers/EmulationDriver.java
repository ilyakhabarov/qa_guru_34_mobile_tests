package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.EmulationConfig;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulationDriver implements WebDriverProvider {

    private static final EmulationConfig config = ConfigFactory.create(EmulationConfig.class);

    @NotNull
    @Override
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.merge(capabilities);

        caps.setCapability("emulation.platform.name", config.platform());
        caps.setCapability("emulation.device.name", config.emulationDevice());
        caps.setCapability("emulation.os", config.emulationOsVersion());
        caps.setCapability("emulation.app.path", new File(config.appPath()).getAbsolutePath());
        caps.setCapability("emulation.setAppPackage", config.emulationSetAppPackage());
        caps.setCapability("emulation.setAppActivity", config.emulationSetAppActivity());
        caps.setCapability("emulation.automation.name", config.automationName());

        try {
            return new AndroidDriver(new URL(config.url()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }
    }
}
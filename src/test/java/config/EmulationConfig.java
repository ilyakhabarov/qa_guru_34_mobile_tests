package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config.properties"
})
public interface EmulationConfig extends Config {

    @Key("emulation.url")
    String url();

    @Key("emulation.platform.name")
    String platform();

    @Key("emulation.device.name")
    String emulationDevice();

    @Key("emulation.os")
    String emulationOsVersion();

    @Key("emulation.setAppPackage")
    String emulationSetAppPackage();

    @Key("emulation.setAppActivity")
    String emulationSetAppActivity();

    @Key("emulation.automation.name")
    String automationName();

    @Key("emulation.app.path")
    String appPath();

}
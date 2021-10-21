package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})

public interface ProjectConfig extends Config {
    @Config.DefaultValue("chrome")
    @Config.Key("browser")
    String browser();

    @Config.DefaultValue("91.0")
    @Config.Key("browserVersion")
    String browserVersion();

    @Config.DefaultValue("1920x1080")
    @Config.Key("browserSize")
    String browserSize();

    @Config.DefaultValue("")
    @Config.Key("browserMobileView")
    String browserMobileView();

    @Config.DefaultValue("")
    @Config.Key("remoteDriverUrl")
    String remoteDriverUrl();

    @Config.Key("videoStorage")
    String videoStorage();
}
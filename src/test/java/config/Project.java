package config;

import org.aeonbits.owner.ConfigFactory;

public class Project {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static boolean isWebMobile() {
        return !config.browserMobileView().equals("");
    }

    public static boolean isRemoteWebDriver() {
        return config.remoteDriverUrl() != null && !config.remoteDriverUrl().isEmpty();
    }

    public static boolean isVideoOn() {
        return !config.videoStorage().equals("");
    }
}
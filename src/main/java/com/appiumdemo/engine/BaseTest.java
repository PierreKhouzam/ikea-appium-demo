package com.appiumdemo.engine;

import com.appiumdemo.utils.Emulators;
import com.appiumdemo.utils.Configs;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    @BeforeSuite
    public void setup() {
        AppiumServer.start();
        Configs.configLoad();
        Emulators.startOrReturnEmulator();
    }

    @BeforeMethod
    public void launchApp() {
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName(Configs.config.getProperty("platform.name"))
                .setPlatformVersion(Configs.config.getProperty("platform.version"))
                .setApp(Configs.config.getProperty("app.path"))
                .setUdid(Emulators.detectRunningEmulator())
                .autoGrantPermissions()
                .disableWindowAnimation();
        AndroidDriver driver = new AndroidDriver(AppiumServer.server, options);
        driver.executeScript("plugin: setWaitPluginProperties", ImmutableMap.of("timeout", 60000, "intervalBetweenAttempts", 200));
        BaseDriver.setDriver(driver);
    }

    @AfterMethod
    public void quitApp() {
        if (BaseDriver.getCurrentDriver() != null) {
            BaseDriver.getCurrentDriver().quit();
        }
    }

    @AfterSuite
    public void TearDown() {
        AppiumServer.stop();
        Emulators.uninstallApp();
        Emulators.stopEmulator();
    }
}


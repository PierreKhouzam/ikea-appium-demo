package com.appiumdemo.engine;

import com.appiumdemo.utils.Constants;
import com.appiumdemo.utils.Emulators;
import com.appiumdemo.utils.Locators;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {


    @BeforeSuite
    public void startServer() throws InterruptedException {
        AppiumServer.start();
        Emulators.startOrReturnEmulator();
        Locators.locatorsConfigLoad();
    }

    @BeforeMethod
    public void launchApp() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(Constants.appPath);
        options.setUdid(Emulators.detectRunningEmulator());
        options.autoGrantPermissions();
        options.fullReset();
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
    public void stopServer() {
        AppiumServer.stop();
        Emulators.stopEmulator();
    }
}


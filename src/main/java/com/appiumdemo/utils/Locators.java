package com.appiumdemo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Locators {

    public static Properties locatorsConfig;

    //Method to load locators
    public static void locatorsConfigLoad() {
        try {
            FileInputStream input = new FileInputStream(Constants.locatorsConfig);
            locatorsConfig = new Properties(System.getProperties());
            locatorsConfig.load(input);
            Logs.info("Locators config loaded");
        } catch (Exception e) {
            Logs.error("Couldn't load locators config " + e.getMessage());
        }
    }
}

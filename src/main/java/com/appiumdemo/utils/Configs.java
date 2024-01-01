package com.appiumdemo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Configs {

    public static Properties config;


    //Method to load locators
    public static void configLoad() {
        try {
            FileInputStream input = new FileInputStream(Constants.configsPath);
            config = new Properties(System.getProperties());
            config.load(input);
            Logs.info("Config loaded");
        } catch (Exception e) {
            Logs.error("Couldn't load config " + e.getMessage());
        }
    }
}

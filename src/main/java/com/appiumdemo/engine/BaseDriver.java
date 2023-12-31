package com.appiumdemo.engine;

import io.appium.java_client.android.AndroidDriver;

public class BaseDriver {
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    private static BaseDriver instance = null;

    private BaseDriver(){

    }

    public static BaseDriver getInstance(){
        if(instance==null){
            instance = new BaseDriver();
        }
        return instance;
    }

    public AndroidDriver getDriver(){
        return driver.get();
    }

    public static AndroidDriver getCurrentDriver(){
        return getInstance().getDriver();
    }

    public static void setDriver(AndroidDriver Driver){
        driver.set(Driver);
    }
}

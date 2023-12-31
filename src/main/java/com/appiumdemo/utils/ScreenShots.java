package com.appiumdemo.utils;

import com.appiumdemo.engine.BaseDriver;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShots {

    public static void takeScreenshot() {
        try {
            // Take base64Screenshot screenshot for extent reports
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) BaseDriver.getCurrentDriver()).
                    getScreenshotAs(OutputType.BASE64);
            // extent reports log and screenshot operations for test steps
            Extent.getTest().log(Status.INFO, "Screenshot attached");
            Extent.getTest().addScreenCaptureFromBase64String(base64Screenshot);

        } catch (Exception e) {
            Logs.error("Failed to take screenshot " + e.getMessage());
        }
    }
}


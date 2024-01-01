package com.appiumdemo.utils;

import com.appiumdemo.engine.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TListeners extends BaseTest implements ITestListener {

    public void onStart(ITestContext context) {
        Logs.startTestSuite(context.getName());
    }

    public void onTestStart(ITestResult result) {
        Logs.info(("*** Running test method " + result.getMethod().getDescription() + " ***"));
        Extent.startTest(result.getMethod().getDescription());
        VideoRecords.startRecording();
    }


    public void onTestSuccess(ITestResult result) {
        Logs.info("*** Test execution passed for " + result.getMethod().getDescription() + " ***");
        Extent.getTest().log(Status.PASS, result.getMethod().getDescription());
        ScreenShots.takeScreenshot();
        VideoRecords.stopAndSaveRecording(result.getMethod().getMethodName());

    }

    public void onTestFailure(ITestResult result) {
        Logs.info("*** Test execution failed for " + result.getMethod().getDescription() + " ***");
        Extent.getTest().log(Status.FAIL, result.getMethod().getDescription()
                + "\nReason: " + result.getThrowable().getMessage());
        ScreenShots.takeScreenshot();
        VideoRecords.stopAndSaveRecording(result.getMethod().getMethodName());
    }


    public void onTestSkipped(ITestResult result) {
        Logs.info("*** Test " + result.getMethod().getDescription() + " skipped ***");
        ScreenShots.takeScreenshot();
        VideoRecords.stopAndSaveRecording(result.getMethod().getMethodName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Logs.info("*** Test failed but within percentage % " + result.getMethod().getDescription() + " ***");
        ScreenShots.takeScreenshot();
        VideoRecords.stopAndSaveRecording(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        Logs.info(("*** Test Suite " + context.getName() + " ending ***"));
        Reports.getReport().flush();
        File htmlFile = new File(Constants.reportsPath);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            Logs.error("*** Couldn't fire report " + e);
        }
    }
}


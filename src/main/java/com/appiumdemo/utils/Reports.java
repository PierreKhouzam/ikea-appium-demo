package com.appiumdemo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Reports {

    static ExtentReports report;

    public synchronized static ExtentReports getReport() {
        if (report == null) {
            // set HTML report file location
            ExtentSparkReporter spark = new ExtentSparkReporter(Constants.reportsPath);
            report = new ExtentReports();
            report.attachReporter(spark);
        }
        return report;
    }
}
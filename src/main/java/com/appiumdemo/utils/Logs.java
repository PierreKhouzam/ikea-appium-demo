package com.appiumdemo.utils;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;



public class Logs extends SoftAssert {

    //Initialize Log4j2
    private static final Logger log = LogManager.getLogger(Logs.class.getName());


    //This is to print logs at the start of test suite
    public static void startTestSuite(String testSuiteName) {
        log.info("*** Test Suite " + testSuiteName + " started ***");
    }

    //Other useful methods
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    //Methods triggered on test results
    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        Logs.info(assertCommand.getMessage() + " <PASSED> ");
        Extent.getTest().log(Status.PASS, assertCommand.getMessage());
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        String suffix =
                String.format(
                        "Expected [%s] but found [%s]",
                        assertCommand.getExpected().toString(), assertCommand.getActual().toString());
        Logs.error(assertCommand.getMessage() + " <FAILED>. " + suffix);
        Extent.getTest().log(Status.FAIL, assertCommand.getMessage() + ": " + suffix);
    }

}

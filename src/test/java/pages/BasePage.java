package pages;

import com.appiumdemo.engine.BaseDriver;
import com.appiumdemo.utils.Configs;
import com.appiumdemo.utils.Extent;
import com.appiumdemo.utils.Logs;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Map;
import java.util.Properties;


public class BasePage {
    AndroidDriver driver = BaseDriver.getCurrentDriver();
    Properties locators = Configs.config;


    // Method for dynamic locator handling
    public By getByLocator(String locatorType, String locatorValue) {
        By by;
        if (locatorType.equalsIgnoreCase("id")) {
            by = By.id(locatorValue);
        } else if (locatorType.equalsIgnoreCase("xpath")) {
            by = By.xpath(locatorValue);
        } else {
            throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
        return by;
    }

    //Click method
    public void click(String locatorType, String object) {
        try {
            By by = getByLocator(locatorType, locators.getProperty(object));
            driver.findElement(by).click();
            Logs.info("Clicking on element " + object);
            Extent.getTest().log(Status.PASS, "Clicking on element " + object);
        } catch (Exception e) {
            Logs.error("Failed to click on element " + object + ", while error is " + e);
            Extent.getTest().log(Status.FAIL, "Failed to click on element " + object);
        }
    }

    //Write method
    public void write(String locatorType, String object, String key) {
        try {
            By by = getByLocator(locatorType, locators.getProperty(object));
            driver.findElement(by).sendKeys(key);
            Logs.info("Writing " + key + " in " + object);
            Extent.getTest().log(Status.PASS, "Writing " + key + " in " + object);
        } catch (Exception e) {
            Logs.error("Failed to write in " + object + ", while error is " + e);
            Extent.getTest().log(Status.FAIL, "Failed to write in " + object);
        }
    }

    //Get element attribute method
    public String getAttribute(String locatorType, String object, String attribute) {
        try {
            By by = getByLocator(locatorType, locators.getProperty(object));
            String value = driver.findElement(by).getAttribute(attribute);
            Logs.info("Getting " + attribute + " attribute for " + object);
            Extent.getTest().log(Status.PASS, "Getting " + attribute + " attribute for " + object);
            return value;
        } catch (Exception e) {
            Logs.error("Failed to get " + attribute + " attribute for " + object + ", while error is " + e);
            Extent.getTest().log(Status.FAIL, "Failed to get " + attribute + " attribute for " + object);
            return null;
        }
    }

    //Method to select from picker using appium gestures
    public void longPressPicker(String locatorType, String object) {
        try {
            By by = getByLocator(locatorType, locators.getProperty(object));
            RemoteWebElement newQuantity = (RemoteWebElement) driver.findElement(by);
            driver.executeScript("gesture: longPress", Map.of("elementId", newQuantity.getId(), "pressure", 0.4, "duration", 440));
            Logs.info("Selected 2 from quantity picker");
            Extent.getTest().log(Status.PASS, "Selected 2 from quantity picker");
        } catch (Exception e) {
            Logs.error("Couldn't select from picker");
            Extent.getTest().log(Status.FAIL, "Couldn't select from picker");
        }
    }


    //Check element is displayed method
    public Boolean isDisplayed(String locatorType, String object) {
        try {
            By by = getByLocator(locatorType, locators.getProperty(object));
            driver.findElement(by).isDisplayed();
            Logs.info("Element is displayed for " + object);
            Extent.getTest().log(Status.PASS, "Element is displayed for " + object);
            return true;
        } catch (Exception e) {
            Logs.info("Element isn't displayed for " + object);
            return false;
        }
    }
}

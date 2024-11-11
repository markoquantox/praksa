package org.example.Utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;

import org.testng.internal.Utils;

import java.lang.reflect.Method;
import java.util.*;

class WebDriverFactory extends BaseClass {




    /**
     * Browser can be Chrome/Firefox for Locally ran Tests
     * Value "Browserstack" will have its own logic, where we then check <Param: Device> and add values from Devices.JSON
     *
     * @param type   Browser value set in XML Suite as <Param: Browser>
     * @param method Test Method
     * @return RemoteWebDriver session
     */
    @SuppressWarnings("unchecked")
    WebDriver create(String type, Method method) throws IllegalAccessException {

        WebDriver driver = null;

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Integer> contentSettings = new HashMap<>();
        HashMap<String, Object> profile = new HashMap<>();
        HashMap<String, Object> chromePrefs = new HashMap<>();


                System.setProperty("webdriver.chrome.driver.quit.timeout", "5000");

                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-search-engine-choice-screen");
                options.setAcceptInsecureCerts(true);

                // Set Chromedriver to only log Severe level logs, and to System.out stream
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .withLogOutput(System.out)
                        .withLogLevel(ChromiumDriverLogLevel.OFF)
                        .build();

        return driver;
    }
}

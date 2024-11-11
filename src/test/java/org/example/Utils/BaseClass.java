package org.example.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.CaseFormat;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


import static org.example.Utils.Platform.WEB_DESKTOP;

public class BaseClass implements Waiter {

    public static WebDriver driver;
    public static ThreadLocal<String> testPlatform = new ThreadLocal<>();
    private final String fs = File.separator;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    public BaseClass() {
        // Default constructor
    }

    public BaseClass(WebDriver driver){
        BaseClass.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }


    /**
     * Getter Method for the Platform that is set using setPlatform() method
     *
     * @return
     */
    public static Platform getPlatform() {
        try {
            return Platform.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, testPlatform.get()));
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Setter Method for the Platform the Test is intended to run on
     * In "setup()" Method, set the platform based on the Groups present on the Test
     */
    protected void setPlatform() {
        String platform = WEB_DESKTOP.toString();
        testPlatform.set(platform);
    }


    @BeforeTest
    public void beforeTest() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + fs + "reports" + fs + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Test Done By", "Marko Zdravkovic");
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethod(@Optional("Chrome") String browser, Method m, ITestResult iTestResult) {
        logger = extent.createTest(m.getName());
        setPlatform();
        setDriver(); // Ensure the driver is initialized

        switch (Objects.requireNonNull(getPlatform())) {
            case WEB_DESKTOP:
                driver.manage().window().setSize(new Dimension(1920, 1080));
                break;
            case WEB_PHONE:
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waitImplicit(5000);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: " + methodName + " passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: " + methodName + " failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }


}

package org.example.Utils;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer extends BaseClass implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry = 1;

    public RetryAnalyzer(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean retry(ITestResult iTestResult)  {
        while(count<maxTry){
            count++;
            return true;
        }
        return false;
    }

}

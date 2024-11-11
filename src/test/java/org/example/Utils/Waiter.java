package org.example.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Utils.BaseClass.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public interface Waiter {

    default WebDriverWait waiter() {
        return new WebDriverWait(driver, Duration.ofSeconds(70), Duration.ofMillis(500));
    }

    default WebDriverWait waiter(int timeUnit) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeUnit), Duration.ofMillis(500));
    }

    default WebDriverWait waiter(int timeUnit, int sleepInMillis) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeUnit), Duration.ofMillis(sleepInMillis));
    }


    default JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) driver;
    }

    /**
     * Waits implicit wait argument
     */
    public default void waitImplicit(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public default void waitUntilElementIsVisible(WebElement element) {
        waiter().until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element)));
    }


    default void waitUntilElementIsPresent(WebElement element) {
        String tag = element.getTagName();
        String attr;
        if (element.getAttribute("id").equals("")) {
            attr = "." + element.getAttribute("class").replaceAll(" ", ".");
        } else {
            attr = "#" + element.getAttribute("id");
        }

        waiter().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tag + attr)));
    }


    default boolean waitUntilAttributeContainsBool(WebElement locator, String text, String attribute) {
        try {
            waiter().until(ExpectedConditions.attributeContains(locator, attribute, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    default void waitUntilElementContainsAttribute(WebElement element, String attribute, String value) {
        waiter().until(ExpectedConditions.attributeContains(element, attribute, value));
    }


    default boolean waitUntilUrlContainsBool(String url) {
        try {
            return waiter().until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            return false;
        }
    }

    default boolean waitUntilUrlToNotContainsBool(String url) {
        try {
            return waiter().until(ExpectedConditions.not(ExpectedConditions.urlContains(url)));
        } catch (TimeoutException e) {
            return false;
        }
    }


    default boolean waitUntilElementIsVisible(int seconds, WebElement element) {
        int counter = (seconds / 3);
        for (int i = 0; i < counter; i++) {
            try {
                waitImplicit(3000);
                return element.isDisplayed();
            } catch (NoSuchElementException ignored) {
            }
        }
        return false;
    }


    default void waitUntilAttributeContains(By locator, String text, String attribute) {
        waiter().until(ExpectedConditions.attributeContains(locator, attribute, text));
    }

    default void waitUntilAttributeToNotContain(WebElement element, String attribute, String attributeText) {
        waiter().until((ExpectedCondition<Boolean>) driver -> !element.getAttribute(attribute).contains(attributeText));
    }


    default boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    default boolean isElementPresent(WebElement element) {
        try {
            waiter().until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    default boolean isElementClickable(WebElement element) {
        try {
            waiter().until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    default void waitUntilElementDisappears(WebElement element) {

        waiter().until(ExpectedConditions.invisibilityOf(element));
    }


    /**
     * Wait until element is clickable
     *
     * @param element Web element
     */
    public default void waitUntilElementIsClickable(WebElement element) {
        waiter().until(ExpectedConditions.elementToBeClickable(element));
    }


    default boolean isAlertPresentBool(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Wait until an Alert is present, return Boolean at the end of the wait
     */
    default boolean waitUntilAlertIsPresentBoolean() {
        try {
            waiter().until(alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    /**
     * Execute JavaScript click when element is not clickable.
     * NOTE: Should be used carefully! If web element does not have 'id' tag
     * Method will use web element's class. A couple of elements can have the same class.
     * In that case, method will not always click wanted element.
     *
     * @param element Web element
     */
    public default void jsElementClick(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scroll to an element and postions it in the center of the page
     */
    public default void scrollToElementCenter(WebElement element) {
        try {
            getExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scroll to the bottom of the page
     */
    default void scrollToTheBottomOfThePage() {
        getExecutor().executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

    /**
     * Scroll to the top of the page
     */
    default void scrollToTheTopOfThePage() {
        getExecutor().executeScript("window.scrollTo(0, 0);", "");
    }

    /**
     * Hover web element provided
     */
    default void hoverWebElement(WebElement element) {

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        waitImplicit(300);
    }

}

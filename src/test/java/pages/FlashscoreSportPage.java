package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FlashscoreSportPage extends BaseClass {

    //Web Elements
    @WebElementLocator(webDesktop = "//span[contains(text(),'display matches')]")
    private List<WebElement> dropDownMoreDisplayMatches() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "dropDownMoreDisplayMatches")));
    }

    @WebElementLocator(webDesktop = "//div[contains(text(),'Finished')]//ancestor::div[@class='event__match event__match--withRowLink event__match--twoLine']")
    private List<WebElement> finishedMatchesList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "finishedMatchesList")));
    }

    @WebElementLocator(webDesktop = "//div[contains(text(),'Finished')]//ancestor::div[contains(@class,'event__match')]//following-sibling::div[contains(@class, 'event__score--home')]")
    private List<WebElement> scoreAtHomeList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "scoreAtHomeList")));
    }

    @WebElementLocator(webDesktop = "//div[contains(text(),'Finished')]//ancestor::div[contains(@class,'event__match')]//following-sibling::div[contains(@class, 'event__score--away')]")
    private List<WebElement> scoreAwayList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "scoreAwayList")));
    }

    @WebElementLocator(webDesktop = "//div[contains(text(),'Finished')]//ancestor::div[contains(@class,'event__match')]//following-sibling::div[contains(@class, 'event__participant--home')]")
    private List<WebElement> participantAtHomeList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "participantAtHomeList")));
    }

    @WebElementLocator(webDesktop = "//div[contains(text(),'Finished')]//ancestor::div[contains(@class,'event__match')]//following-sibling::div[contains(@class, 'event__participant--away')]")
    private List<WebElement> participantAwayList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "participantAwayList")));
    }

    //Functions

    /**
     * Function that checks for drop down element list
     * and clicking on elements, so we can see all Finished games
     */

    public void clickAllDropDownMoreDisplayMatches() {
        if (dropDownMoreDisplayMatches().isEmpty()) {
            System.out.println("Drop downs not founded");
        } else for (WebElement element : dropDownMoreDisplayMatches()) {
            scrollToElementCenter(element);
            element.click();
            ;
        }
    }

    /**
     * Function/Assert for checking if finished game list is empty
     */
    public void finishedMatchesListEmpty() {
        Assert.assertFalse(finishedMatchesList().isEmpty(), "There is no finished matches");
    }

    /**
     * Function parsing string elements to integer value
     *
     * @return List of Integer values
     */
    public List<Integer> sHomeList() {
        List<Integer> x = new ArrayList<>();
        for (WebElement element : scoreAtHomeList()) {
            x.add(parseInt(element.getText()));
        }
        return x;
    }

    /**
     * Function parsing string elements to integer value
     *
     * @return List of Integer values
     */
    public List<Integer> sAwayList() {
        List<Integer> x = new ArrayList<>();
        for (WebElement element : scoreAwayList()) {
            x.add(parseInt(element.getText()));
        }
        return x;
    }

    /**
     * Function for comparing lists of integer vales
     * and printing out results from one of participant lists(elements)
     */

    public void compare() {
        for (int i = 0; i < sHomeList().size(); i++) {
            if (sHomeList().get(i) > sAwayList().get(i)) {
                System.out.println("The winner of the match is: " + participantAtHomeList().get(i).getText());
            } else if (sHomeList().get(i) < sAwayList().get(i)) {
                System.out.println("The winner of the match is: " + participantAwayList().get(i).getText());
            } else
                System.out.println("The match is tied: " + "Home Team: " + participantAtHomeList().get(i).getText() + " Away Team: " + participantAwayList().get(i).getText());
        }
    }


}

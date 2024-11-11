package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FlashscoreComPage extends BaseClass {

    //Web Elements
    @WebElementLocator(webDesktop = "button#onetrust-accept-btn-handler")
    private WebElement banner() {
        return driver.findElement(By.cssSelector(new WebElementLocatorFactory().getLocator(FlashscoreComPage.class, "banner")));
    }

    @WebElementLocator(webDesktop = ".menuMinority__title")
    private WebElement moreButton() {
        return driver.findElement(By.cssSelector(new WebElementLocatorFactory().getLocator(FlashscoreComPage.class, "moreButton")));
    }

    @WebElementLocator(webDesktop = "//a[not(@class='menuMinority__item menuMinority__item--hidden')][@data-sport-id][not(@class='menuMinority__item--active menuMinority__item menuMinority__item--hidden')]")
    private List<WebElement> sports() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreComPage.class, "sports")));
    }

    //Functions

    /**
     * Function for clicking on banner for Accepting terms of services
     */

    public void clickOnAcceptBanner() {
        banner().click();
    }

    /**
     * Function clicking on more button(Sports), so we can interact with them
     */

    public void clickOnMoreButton() {
        moreButton().click();
        scrollToElementCenter(moreButton());
    }

    /**
     * Function for sports list size
     * @return list size
     */

    public int sportsListLenght() {
        return sports().size();
    }

    /**
     * Function defining random number in integer value, depends on list size
     *
     * @return random Integer number
     */
    public int randomNumber() {
        return (int) (Math.floor(Math.random() * sportsListLenght()));
    }

    /**
     * Function for clicking on random element, depends on random number function
     */
    public void clickRandomSport() {
        sports().get(randomNumber()).click();
    }
}

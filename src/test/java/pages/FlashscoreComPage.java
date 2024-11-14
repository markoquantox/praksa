package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.NavigationPage;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FlashscoreComPage extends BaseClass {

    NavigationPage navigationPage = new NavigationPage();

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

    @WebElementLocator(webDesktop = "//a[not(contains(@class,'menuMinority__item--hidden'))][not(@class='menuTop__item menuTop__myfs')][@data-sport-count]")
    private List<WebElement> listValuesBubbles() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreComPage.class, "listValuesBubbles")));
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
    public int randomNumber(int x) {
        return (int) (Math.floor(Math.random() * x));
    }

    /**
     * Function for clicking on random element, depends on random number function
     */
    public void clickRandomSport() {
        sports().get(randomNumber(sportsListLenght())).click();
    }

    /**
     * Getting attributes and sorting them, so we can isolate the ons with more matches than 5 in them
     * @return List with sorted elements
     */
    public List<String> sortedElements() {
        List<String> list1 = new ArrayList<>();
        for (WebElement element : listValuesBubbles()) {
            if (parseInt(element.getAttribute("data-sport-count")) >= 5) {
                list1.add(element.getAttribute("href"));
            }
        } return list1;
    }

    /**
     * Making the list of all matches elements on the current page and clicking on random favorite button of an element
     */
    public void clickOnRandomFavorieMatch(){
        List<WebElement> a =driver.findElements(By.xpath("//button[@data-state='closed']"));
        waitImplicit(1000);
        a.get(randomNumber(a.size())).click();
    }

    /**
     * Integrating previous methods, so we can navigate to the pages that
     * have more than 5 matches in them, and clicking on random favorite button on those pages
     */
    public void clickOnElementsFormSortedListAndFavoriteMatches() {
        for (String x: sortedElements()) {
            waitImplicit(5000);
            navigationPage.navigateToPage(x);
            waitImplicit(5000);
            clickOnRandomFavorieMatch();
            waitImplicit(3000);
        }
    }

    /**
     * Asserting if number of elements in favorite element
     * is equal to the number of elements in the sorted list.
     * Checking like that if the number of elements in Favorites element is equal to the number of elements we added
     */
    public void benchmarkingOfFavoriteMatches(){
        driver.navigate().back();
        WebElement x=driver.findElement(By.xpath("//a[@class='menuTop__item menuTop__myfs']"));
        Assert.assertEquals(sortedElements().size(), parseInt(x.getAttribute("data-sport-count")),"The number of items in the bubble is not equal to the number of elements we added!!! ");
        scrollToElementCenter(x);
        waitImplicit(1000);
    }
}

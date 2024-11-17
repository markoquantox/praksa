package pages;


import org.example.Utils.BaseClass;
import org.example.Utils.NavigationPage;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

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
     * Function clicking on more button (Sports), so we can interact with them
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
     * Function for clicking on a random element, depends on random number function
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
     * Creating the empty list, so we can add names to it
     */
    List<String> namesOfParticipant=new ArrayList<>();
    /**
     * Making the list of all matches elements on the current page and clicking on random favorite button of an element
     * Also adding elements to namesOfParticipant list, so we can compare it later
     */
    public void clickOnRandomFavorieMatch(){
        List<WebElement> a =driver.findElements(By.xpath("//div[contains(@class,'event__match--withRowLink')]"));
        List<WebElement> d=driver.findElements(By.xpath("//button[@data-state]"));
        waitImplicit(1000);
        int h=randomNumber(a.size());
        WebElement x=a.get(h);
        scrollToElementCenter(x);
        List<WebElement> f=x.findElements(By.xpath("./div[contains(@class,'event__participant')]"));
        for (WebElement element: f) {
            namesOfParticipant.add(element.getText());
        }
        d.get(h).click();
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
     * Asserting if number of elements in a favorite element
     * is equal to the number of elements in the sorted list.
     * Checking like that if the number of elements in a Favorite element is equal to the number of elements we added
     */
    public void benchmarkingOfFavoriteMatches(){
        driver.navigate().back();
        WebElement x=driver.findElement(By.xpath("//a[@class='menuTop__item menuTop__myfs']"));
        Reporter.log("Asserting if number of elements are the same line kin the favorite element/bubble",true);
        Assert.assertEquals(sortedElements().size(), parseInt(x.getAttribute("data-sport-count")),"The number of items in the bubble is not equal to the number of elements we added!!! ");
        Reporter.log("Scrolling to the element",true);
        scrollToElementCenter(x);
        waitImplicit(1000);
        Reporter.log("Clicking on/adding favorite/star element",true);
        x.click();
        waitImplicit(3000);
        Reporter.log("Exiting add",true);
        popUpExit();
        waitImplicit(3000);
        Reporter.log("Asserting if names from added elements, are contained in a list of names on this page",true);
        Assert.assertTrue(eventParticipanteNames().containsAll(namesOfParticipant),"Lists don't match");
    }

    /**
     * Function for finding an add/exit element and clicking on it
     */
    public void popUpExit(){
        WebElement a=driver.findElement(By.cssSelector(".close.modal__closeButton"));
        a.click();
    }

    /**
     * Function for finding array of elements that contains name of participants
     * @return List of name elements 
     */
    public List<String> eventParticipanteNames(){
        List<String> a= new ArrayList<>();
        List<WebElement>x=driver.findElements(By.xpath("//div[contains(@class,'event__match--withRowLink')]/div[contains(@class,'event__participant')]"));
        for (WebElement element: x){
            try {
                a.add(element.getText());
            } catch (StaleElementReferenceException ignored){
            }
        } return a;
    }
}
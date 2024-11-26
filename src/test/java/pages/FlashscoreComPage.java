package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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

    @WebElementLocator(webDesktop = "//a[not(contains(@class,'menuMinority__item--hidden'))][not(@class='menuTop__item menuTop__myfs')][@data-sport-count]")
    private List<WebElement> listValuesBubbles() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreComPage.class, "listValuesBubbles")));
    }

    @WebElementLocator(webDesktop = "//span[contains(text(),'display matches')]")
    private List<WebElement> dropDownMoreDisplayMatches() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreSportPage.class, "dropDownMoreDisplayMatches")));
    }
    @WebElementLocator(webDesktop = "//div[contains(@class,'event__match--withRowLink')]/div[contains(@class,'participant')]")
    private List<WebElement> listAllParticipants() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreFavoritesPage.class, "listAllParticipants")));
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
     *
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

    /**
     * Function for sorting elements that have more than 5 matches
     * @return List of sorted elements
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
     * Function that checks for drop down element list
     * and clicking on elements, so we can see all Finished games
     */

    public void clickAllDropDownMoreDisplayMatches() {
        if (dropDownMoreDisplayMatches().isEmpty()) {
            System.out.println("Drop downs not founded");
        } else for (WebElement element : dropDownMoreDisplayMatches()) {
            scrollToElementCenter(element);
            element.click();
        }
    }
    /**
     * Function for getting attributes of the elements from the list
     * @return List of string attributes
     */

    public List<String>listAllParticipantsToString(){
        List<String> a= new ArrayList<>();
        for (WebElement element: listAllParticipants()){
            try {
                a.add(element.getText());
            } catch (StaleElementReferenceException ignored){
            }
        } return a;
    }
    public void csvCreate(List strings) throws IOException {
        //String csvFile=System.getProperty("user.dir")+"\\data\\file1.csv";
        File obj=new File("C:\\Users\\dzony\\OneDrive\\Radna površina\\Projects\\kloniranaverzija\\praksa\\data\\file1.csv");
        FileOutputStream font=new FileOutputStream(obj);
        //FileWriter writer=new FileWriter(csvFile);
        for (Object x:strings){
            font.write((x +",\n").getBytes());
        }
        font.close();
    }
}



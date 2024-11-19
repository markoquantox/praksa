package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FlashscoreFavoritesPage extends BaseClass {

    //Web Elements

    @WebElementLocator(webDesktop = "//div[contains(@class,'event__match--withRowLink')]/div[contains(@class,'participant')]")
    private List<WebElement> listAllParticipants() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreFavoritesPage.class, "listAllParticipants")));
    }

    //Functions

    /**
     * Function for geting atributes of the elements from the list
     * @return List of string atributes
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
}

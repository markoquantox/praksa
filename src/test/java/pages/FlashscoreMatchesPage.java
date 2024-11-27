package pages;

import org.example.Utils.BaseClass;
import org.example.Utils.WebElementLocator;
import org.example.Utils.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FlashscoreMatchesPage  extends BaseClass {

    //Web Elements
    @WebElementLocator(webDesktop = "//span[contains(@class, 'tournamentHeader__country')]/a")
    private WebElement leagueElement() {
        return driver.findElement(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "leagueElement")));
    }

    @WebElementLocator(webDesktop = "//a[contains(@class, 'participant__participantName')]")
    private List<WebElement> listOfParticipants() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "listOfParticipants")));
    }

    @WebElementLocator(webDesktop = "//div[contains(@class,'wcl-badgeInfo_cdKy0')]/span[contains(@class,'wcl-caption_xZPDJ')]")
    private List<WebElement> teamPositionOnTheList() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "teamPositionOnTheList")));
    }


    //Functions

    public WebElement getleagueElement(){
        return leagueElement();
    }

    public String leagueElementAttribute(){
        return getleagueElement().getText();
    }

    public List<WebElement>getlistOfParticipants(){
        return listOfParticipants();
    }

    public List<String>listOfParticipantsAttributes(){
        List<String>x=new ArrayList<>();
        for(WebElement element :getlistOfParticipants()){
            x.add(element.getText());
        }return x;
    }

    public List<WebElement>getTeamPositionOnTheList(){
        return teamPositionOnTheList();
    }

    public List<Integer>teamPositionOnTheListInteger(){
        List<Integer> x=new ArrayList<>();
        for (WebElement element:getTeamPositionOnTheList()){
            x.add(Integer.parseInt(element.getText().replace(".","")));
        }return x;
    }

}

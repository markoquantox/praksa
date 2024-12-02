package pages;

import org.apache.commons.lang3.StringUtils;
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
    @WebElementLocator(webDesktop = "//button[contains(text(),'Standings')]")
    private WebElement standingsElement() {
        return driver.findElement(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "standingsElement")));
    }
    @WebElementLocator(webDesktop = "//a[@class='oddsCell__odd oddsCell__highlight ']/span")
    private List<WebElement> bestOddsforMatch() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "bestOddsforMatch")));
    }
    @WebElementLocator(webDesktop = "//a[@class='oddsCell__odd oddsCell__highlight ']//ancestor::div[@class='ui-table__row']//a[@class='prematchLink']")
    private List<WebElement> bestOddsforMatchNameOfBettingHouse() {
        return driver.findElements(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "bestOddsforMatchNameOfBettingHouse")));
    }
    @WebElementLocator(webDesktop = "//a[@href='#/odds-comparison']/button")
    private WebElement oddsElement() {
        return driver.findElement(By.xpath(new WebElementLocatorFactory().getLocator(FlashscoreMatchesPage.class, "oddsElement")));
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
            x.add(element.getAttribute("innerHTML"));
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

    public WebElement getStandingsElement(){
        return standingsElement();
    }

    public List<Integer>teamPositionOnTheListIn(){
        List<Integer>x=new ArrayList<>();
        WebElement a=driver.findElement(By.xpath("//a[contains(@class,'tableCellParticipant__name')][contains(text(),'"+listAbbreviateOfParticipantsAttributes().get(0)+"')]//ancestor::div[contains(@class,'ui-table__row')]/div/div[contains(@class,'tableCellRank')]"));
        WebElement b=driver.findElement(By.xpath("//a[contains(@class,'tableCellParticipant__name')][contains(text(),'"+listAbbreviateOfParticipantsAttributes().get(1)+"')]//ancestor::div[contains(@class,'ui-table__row')]/div/div[contains(@class,'tableCellRank')]"));
        x.add(Integer.parseInt(a.getText().replace(".","")));
        x.add(Integer.parseInt(b.getText().replace(".","")));
    return x;
    }

    public List<String>listAbbreviateOfParticipantsAttributes(){
        List<String>x=new ArrayList<>();
        for (String a:listOfParticipantsAttributes()){
            x.add(StringUtils.left(a,4));
        }return x;
    }

    public List<String> listOfbestOddsforMatch(){
        List<String>x=new ArrayList<>();
        for (WebElement element:bestOddsforMatch()){
            x.add(element.getText());
        }return x;
    }

    public List<String> listOFBestOddsforMatchNameOfBettingHouse(){
        List<String>x=new ArrayList<>();
        for (WebElement element:bestOddsforMatchNameOfBettingHouse()){
            x.add(element.getAttribute(""));
        }return x;
    }

    public WebElement getOddsElement(){
        return oddsElement();
    }

}
////a[contains(@class,'tableCellParticipant__name')][contains(text(),'Ararat-Armenia')]//ancestor::div[contains(@class,'ui-table__row')]/div/div[contains(@class,'tableCellRank')]
package Tests;

import org.example.Utils.BaseClass;
import org.example.Utils.Groups;
import org.example.Utils.NavigationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.FlashscoreComPage;
import pages.FlashscoreFavoritesPage;
import pages.FlashscoreSportPage;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FlashscoreTest extends BaseClass {

    //Parms
    private static final String URL_BASE = "https://www.flashscore.com/";

    @Test(groups = Groups.PC, priority = 1, description = "Clicking on random spots and checking status of finished games")
    public void firstTask() {
        //Objects
        NavigationPage navigationPage = new NavigationPage();
        FlashscoreComPage flashscoreComPage = new FlashscoreComPage();
        FlashscoreSportPage flashscoreSportPage = new FlashscoreSportPage();

        //Actions
        Reporter.log("Navigate to https://www.flashscore.com/ page", true);
        navigationPage.navigateToPage(URL_BASE);
        waitImplicit(5000);

        Reporter.log("Navigate to I Accept button on the banner and click on it", true);
        flashscoreComPage.clickOnAcceptBanner();
        waitImplicit(5000);

        Reporter.log("Navigating to More button and clicking on it", true);
        flashscoreComPage.clickOnMoreButton();
        waitImplicit(5000);

        Reporter.log("Getting list and click on random sport", true);
        flashscoreComPage.clickRandomSport();
        waitImplicit(10000);

        Reporter.log("Checking drop down elements and clicking on them if there are present", true);
        flashscoreSportPage.clickAllDropDownMoreDisplayMatches();
        waitImplicit(10000);

        Reporter.log("Finding finished matches :", true);
        flashscoreSportPage.finishedMatchesListEmpty();
        waitImplicit(10000);

        Reporter.log("Loading ", true);
        flashscoreSportPage.compare();
        waitImplicit(10000);
    }



    @Test(groups = Groups.PC, priority = 2, description = "Clicking on random spots and checking status of finished games")
    public void secondTask() {
        //Objects
        NavigationPage navigationPage = new NavigationPage();
        FlashscoreComPage flashscoreComPage = new FlashscoreComPage();
        FlashscoreSportPage flashscoreSportPage = new FlashscoreSportPage();
        FlashscoreFavoritesPage flashscoreFavoritesPage=new FlashscoreFavoritesPage();

        //Actions
        Reporter.log("Navigate to https://www.flashscore.com/ page", true);
        navigationPage.navigateToPage(URL_BASE);
        waitImplicit(5000);

        Reporter.log("Navigate to I Accept button on the banner and click on it", true);
        flashscoreComPage.clickOnAcceptBanner();
        waitImplicit(5000);

        Reporter.log("Navigating to More button and clicking on it", true);
        flashscoreComPage.clickOnMoreButton();
        waitImplicit(5000);

        Reporter.log("Geting true the pages: ",true);
        List<String>participantsList=new ArrayList<>();
        int rendomIndex=0;
        for(String url:flashscoreComPage.sortedElements()){
            waitImplicit(5000);
            Reporter.log("Navigate to next url: "+url,true);
            navigationPage.navigateToPage(url);
            waitImplicit(3000);
            int x=flashscoreSportPage.randomNumber(flashscoreSportPage.getlistOfAllGames().size());
            scrollToElementCenter(flashscoreSportPage.getlistOfAllGames().get(x));
            List<WebElement> f=flashscoreSportPage.getlistOfAllGames().get(x).findElements(By.xpath("./div[contains(@class,'participant')]"));
            for(WebElement element:f) {
                System.out.println(element.getText());
                participantsList.add(element.getText());
            }
            flashscoreSportPage.getlistOfAllFaworiteElements().get(x).click();
            rendomIndex++;
        }
        Reporter.log("Exiting pop up",true);
        flashscoreSportPage.popUpExit();
        waitImplicit(3000);

        Reporter.log("Asserting if number of elements are the same line kin the favorite element/bubble",true);
        Assert.assertEquals(rendomIndex, parseInt(flashscoreSportPage.getFavoritesWebElement().getAttribute("data-sport-count")),"The number of items in the bubble is not equal to the number of elements we added!!!");
        waitImplicit(3000);

        Reporter.log("Clicking on favorites element",true);
        flashscoreSportPage.getFavoritesWebElement().click();
        waitImplicit(3000);

        Reporter.log("Asserting if names from added elements, are contained in a list of names on this page",true);
        Assert.assertTrue(flashscoreFavoritesPage.listAllParticipantsToString().containsAll(participantsList),"Lists don't match");
        waitImplicit(3000);
    }
}
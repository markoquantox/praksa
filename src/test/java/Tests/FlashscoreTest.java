package Tests;

import org.example.Utils.BaseClass;
import org.example.Utils.Groups;
import org.example.Utils.NavigationPage;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.FlashscoreComPage;
import pages.FlashscoreSportPage;

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
}
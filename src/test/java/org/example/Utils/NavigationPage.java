package org.example.Utils;

public class NavigationPage extends BaseClass {
    //Actions
    //All Tasks
    public void navigateToPage(String url){
        getDriver().get(url);
        waitImplicit(10000);
    }
}

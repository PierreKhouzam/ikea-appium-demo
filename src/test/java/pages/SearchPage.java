package pages;

import com.appiumdemo.engine.BaseDriver;

public class SearchPage extends BasePage {

    public SearchPage enterSearchKeyword() {
        write("id","searchField","table");
        return this;
    }

    public ListingPage selectFirstSearchResult() {
        click("xpath", "firstSearchResult");
        return new ListingPage();
    }

    public HomePage goBackToHome() {
        click("id","searchBckBtn");
        BaseDriver.getCurrentDriver().navigate().back();
        return new HomePage();
    }
}

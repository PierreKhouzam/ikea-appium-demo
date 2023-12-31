package pages;

public class HomePage extends BasePage {

    public SearchPage clickSearch() {
        click("id","homeSearch");
        return new SearchPage();
    }

    public CartPage openCart() {
        click("xpath","cartBtn");
        return new CartPage();
    }
}

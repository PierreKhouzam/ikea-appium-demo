package pages;

public class ListingPage extends BasePage {

    public String readProductNameFromListing() {
        return getAttribute("xpath","listingProductName", "text");
    }

    public String readProductPriceFromListing() {
        return getAttribute("xpath","listingProductPrice", "text");
    }

    public DetailsPage pressOnProduct() {
        click("xpath","firstListingProduct");
        return new DetailsPage();
    }

    public SearchPage goBackToSearch() {
        click("id","listingBckBtn");
        return new SearchPage();
    }
}

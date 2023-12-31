package pages;

public class DetailsPage extends BasePage {

    public String readProductNameFromDetailsPage() {
        return getAttribute("id", "detailsProductName", "text");
    }

    public String readProductPriceFromDetailsPage() {
        return getAttribute("id", "detailsProductPrice", "text");
    }

    public DetailsPage addProductToCart() {
        click("id", "addToBagBtn");
        return this;
    }

    public ListingPage goBackToListing() {
        click("id", "detailsBckBtn");
        return new ListingPage();
    }

}

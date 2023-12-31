package pages;


public class CartPage extends BasePage {

    public CheckoutPage proceedToCheckout() {
        click("id", "checkoutBtn");
        return new CheckoutPage();
    }

    public CartPage setQuantity() {
        click("id", "productQuantityBtn");
        longPressPicker("xpath", "newQuantity");
        click("id", "okBtn");
        return new CartPage();
    }

    public CartPage removeFromCart() {
        click("id", "removeProductBtn");
        return this;
    }

    public CartPage confirmToRemove() {
        click("id", "confirmRemovalBtn");
        return this;
    }

    public boolean recommendedSectionIsDisplayed() {
        return isDisplayed("id", "recommendedLabel");
    }

}


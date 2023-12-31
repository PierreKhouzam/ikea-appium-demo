package pages;

public class CheckoutPage extends BasePage {


    public CheckoutPage discardCheckout() {
        click("xpath", "discardCheckoutBtn");
        return this;
    }

    public CartPage confirmToDiscardCheckout() {
        click("id", "ConfirmDiscardBtn");
        return new CartPage();
    }
}

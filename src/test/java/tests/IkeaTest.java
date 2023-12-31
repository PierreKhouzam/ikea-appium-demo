package tests;

import com.appiumdemo.engine.BaseTest;
import com.appiumdemo.utils.Logs;
import com.appiumdemo.utils.TListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners({TListeners.class})
public class IkeaTest extends BaseTest {
    Logs assertion = new Logs();

    @Test(description = "TC1: Validate that product's details consistency")
    public void firstTestCase() {

        HomePage hp = new HomePage();
        hp.clickSearch().enterSearchKeyword().selectFirstSearchResult();

        ListingPage lp = new ListingPage();
        String listingProductName = lp.readProductNameFromListing();
        String listingProductPrice = lp.readProductPriceFromListing();
        lp.pressOnProduct();

        DetailsPage dp = new DetailsPage();
        String detailsProductName = dp.readProductNameFromDetailsPage();
        String detailsProductPrice = dp.readProductPriceFromDetailsPage();

        assertion.assertEquals(listingProductName, detailsProductName, "Validation: Product name in listing page matches product name in details page");
        assertion.assertEquals(listingProductPrice, detailsProductPrice, "Validation: Product price in listing page matches product price in details page");
        assertion.assertAll();
    }

    @Test(description = "TC2: Validate that cart is empty post product removal")
    public void secondTestCase() {

        HomePage hp = new HomePage();
        hp.clickSearch().enterSearchKeyword().selectFirstSearchResult().
                pressOnProduct().addProductToCart().goBackToListing().
                goBackToSearch().goBackToHome().openCart().setQuantity().
                proceedToCheckout().discardCheckout().confirmToDiscardCheckout();

        CartPage cp = new CartPage();
        cp.removeFromCart().confirmToRemove();
        boolean recommendedIsDisplayed = cp.recommendedSectionIsDisplayed();
        assertion.assertTrue(recommendedIsDisplayed, "Validation: Product is no longer displayed & Recommended section is displayed in cart");
        assertion.assertAll();
    }
}

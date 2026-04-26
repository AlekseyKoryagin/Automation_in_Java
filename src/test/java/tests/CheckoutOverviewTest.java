package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.GOODS_NAME;
import static user.UserFactory.*;

public class CheckoutOverviewTest extends BaseTest {
    @Test
    public void checkAmoundsInOverview() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME);
        double totalCost = productsPage.getTotalCostOfGoods(productsPage.getProductPriceList(GOODS_NAME));
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();
        checkoutYourInformationPage.sendFilledForm(infoWithCorrectData());

        assertTrue(checkoutOverviewPage.isFinishBtnDisplayed(), "The finish button did not appear.");
        assertTrue(checkoutOverviewPage.isFinishBtnEnabled(), "The finish button is not enabled.");
        assertEquals(checkoutOverviewPage.getFinishBtnName(), "Finish", "The name of the button is not Finish.");
        assertTrue(checkoutOverviewPage.isSameCost(totalCost), "The amounts don't match.");
        assertTrue(checkoutOverviewPage.isTotalEqualCostAndTax(), "The total amount does not match the amount of tax and the cost of goods.");
    }
}

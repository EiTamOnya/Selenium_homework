package Homeworks;

import Pages.ProductPage;
import Pages.SearchPage;
import Pages.ShopHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.Browser;

public class HomeworkLecture7 {
    private static final String searchWord = "iPhone";
    private static final String qty = "36";

    @BeforeClass
    public void setUp() {
        Browser.open("firefox");
    }

    @Test
    public void searchAndCheck() {
        ShopHomePage.goTo();
        ShopHomePage.searchItem(searchWord);
        SearchPage.checkFirstItem(searchWord);
    }

    @Test
    public void checkCartQuantityAndPrice(){
        ProductPage.goToIphonePage();
        ProductPage.inputQuantityAndAddToCart(qty);
        ProductPage.checkQuanityAndPrice(qty);
    }

    @Test
    public void checkReviewAlert(){
        ProductPage.goToIphonePage();
        ProductPage.addReviewToProductPage("Pavel", "Too short", "5");
        ProductPage.checkAlertReviewMessage("Warning: Review Text must be between 25 and 1000 characters!");
    }

    @AfterClass
    public void tearDown() {
        Browser.quit();
    }
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Browser;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPage {
    private static final By LOC_QUANTITY_INPUT = By.id("input-quantity");
    private static final By LOC_ADD_BUTTON = By.id("button-cart");
    private static final By LOC_CART_BUTTON = By.id("cart-total");
    private static final By LOC_PRICE = By.cssSelector(".list-unstyled li h2");
    private static final By LOC_REVIEW_TAB = By.cssSelector("a[href='#tab-review']");
    private static final By LOC_NAME_INPUT = By.id("input-name");
    private static final By LOC_REVIEW_INPUT = By.id("input-review");
    private static final By LOC_REVIEW_BTN = By.id("button-review");
    private static final By LOC_REVIEW_ALERT = By.cssSelector(".alert");

    /**
     * Method which takes you to the iphone product page
     */
    public static void goToIphonePage() {
        Browser.driver.get("http://shop.pragmatic.bg/index.php?route=product/product&product_id=40");
    }

    /**
     * Method adds a product in the current product page to the cart
     *
     */
    public static void addToCart(){
        Browser.driver.findElement(LOC_ADD_BUTTON).click();
    }

    /**
     * Method inputs quantity and adds to items to the cart
     *
     * @param quantity  the number of items that will be added
     */
    public static void inputQuantityAndAddToCart(String quantity){
        Browser.driver.findElement(LOC_QUANTITY_INPUT).sendKeys(quantity);
        addToCart();
    }

    /**
     * Method checks cart quantity and total price
     *
     * @param expectedQuantity  the number of items that should be added
     */
    public static void checkQuanityAndPrice(String expectedQuantity){
        Pattern cartPattern = Pattern.compile("^(?<qty>[0-9]*)\\|(?<price><=\\$).*");

        String price = Browser.driver.findElement(LOC_PRICE).getText().replace("$","");
        Float finalPrice = Float.parseFloat(price) * Float.parseFloat(expectedQuantity);

        String cartText = Browser.driver.findElement(LOC_CART_BUTTON).getText();

        Matcher m = cartPattern.matcher(cartText);
        while (m.find()){
            System.out.println(cartText);
            System.out.println(m.group());
            assertEquals(m.group("qty"), expectedQuantity);
            assertEquals(Float.parseFloat(m.group("price")), finalPrice);
        }
    }

    /**
     * Method adds a review to a product page
     *
     * @param name       the name of the reviewer
     * @param reviewText the text of the review
     * @param score      the review score, should be between 1 - 5
     */
    public static void addReviewToProductPage(String name, String reviewText, String score) {
        By REVIEW_SCORE = By.cssSelector("input[value='" + score + "']");
        Browser.driver.findElement(LOC_REVIEW_TAB).click();
        Browser.driver.findElement(LOC_NAME_INPUT).sendKeys(name);
        Browser.driver.findElement(LOC_REVIEW_INPUT).sendKeys(reviewText);
        Browser.driver.findElement(REVIEW_SCORE).click();
        Browser.driver.findElement(LOC_REVIEW_BTN).click();
    }
    /**
     * Method checks alert review message
     *
     *
     * @param messageText the expect text of the message
     *
     */
    public static void checkAlertReviewMessage(String messageText) {
        String alert = Browser.driver.findElement(LOC_REVIEW_ALERT).getText();
        assertTrue(alert.contains(messageText));
    }
}

package Pages;

import org.openqa.selenium.By;
import utils.Browser;

public class ShopHomePage {
    private static final By LOC_SEARCH_BAR = By.cssSelector("#search input");
    private static final By LOC_SEARCH_BUTTON = By.cssSelector("#search span button");
    /**
     * Method which takes you to the shop home page!
     */
    public static void goTo() {
        Browser.driver.get("http://shop.pragmatic.bg/");
    }

    /**
     * Method which searches for an item via the search bar
     *
     * @param item  the name of the to be searched item
     */
    public static void searchItem(String item){
    Browser.driver.findElement(LOC_SEARCH_BAR).sendKeys(item);
    Browser.driver.findElement(LOC_SEARCH_BUTTON).click();
    }
}

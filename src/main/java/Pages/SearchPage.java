package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Browser;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchPage {

    private static final By LOC_DISPLAYED_PRODUCTS = By.cssSelector(".caption");

    /**
     * Method assert that the first search result is correct
     *
     * @param expectedName  the name which will be used in the assertion
     */
    public static void checkFirstItem(String expectedName){
        List<WebElement> products = Browser.driver.findElements(LOC_DISPLAYED_PRODUCTS);
        assertEquals(products.get(0).findElement(By.cssSelector("a")).getText(), expectedName);
    }

    /**
     * Method clicks on the first item of the search
     *
     */
    public static void goToFirstItem(){
        List<WebElement> products = Browser.driver.findElements(LOC_DISPLAYED_PRODUCTS);
        products.get(0).findElement(By.cssSelector("a")).click();
    }
}

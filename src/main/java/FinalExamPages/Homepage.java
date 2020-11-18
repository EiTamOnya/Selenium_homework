package FinalExamPages;

import org.openqa.selenium.By;
import utils.Browser;

public class Homepage {
    private static final By LOC_MYACC_DROPDOWN = By.cssSelector(".dropdown a[title='My Account']");
    private static final By LOC_REGISTER_LINK = By.xpath("//a[text()='Register']");

    /**
     * Method which takes you to the shop home page!
     */
    public static void goTo() {
        Browser.driver.get("http://shop.pragmatic.bg/");
    }

    /**
     * Method which takes you to the register account page
     */
    public static void goToRegisterPage() {
        Browser.driver.findElement(LOC_MYACC_DROPDOWN).click();
        Browser.driver.findElement(LOC_REGISTER_LINK).click();
    }
}

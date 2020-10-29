package Homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


public class HomeworkLecture4 {
    static WebDriver driver;

    String backendURL = "http://shop.pragmatic.bg/admin";
    String username = "admin";
    String password =  "parola123!";



    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\drivers\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void LogInBackEnd() {
        String expectedName = "Milen Strahinski";

        driver.get(backendURL);

        Methods.logIn(username,password, driver);

        String currentName = driver.findElement(By.id("user-profile")).getAttribute("alt");
        assertEquals(currentName, expectedName);

        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void WrongCredentials() {
        String expectedAlert = "No match for Username and/or Password.";
        driver.get(backendURL);

        Methods.logIn("blabla","blabla", driver);

        String currentAlert = driver.findElement(By.className("alert")).getText();
        assertTrue(currentAlert.contains(expectedAlert));
    }

    @Test
    public void CheckOrderStatusValues() {
        List<String> exp_options = Arrays.asList(
                "", "Missing Orders", "Canceled", "Canceled Reversal", "Chargeback",
                "Complete", "Denied", "Expired", "Failed", "Pending", "Processed",
                "Processing", "Refunded", "Reversed", "Shipped", "Voided");
        List<String> act_options = new ArrayList<String>();

        driver.get(backendURL);

        Methods.logIn(username,password, driver);

        driver.findElement(By.id("menu-sale")).click();
        driver.findElement(By.linkText("Orders")).click();

        WebElement dropDown = driver.findElement(By.id("input-order-status"));
        Select orderStatus = new Select(dropDown);

        assertFalse(orderStatus.isMultiple());
        assertEquals(orderStatus.getOptions().size(), exp_options.size());

        List<WebElement> allOptions = orderStatus.getOptions();

        for (WebElement option : allOptions) {
            act_options.add(option.getText());
        }
        assertEquals(act_options.toArray(), exp_options.toArray());

        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void CheckMultipleSelect() {
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
        List<String> exp_options = Arrays.asList("Red", "Silver");
        List<String> act_options = new ArrayList<String>();

        List<WebElement> options = driver.findElements(By.cssSelector("select[name='color'] option"));


        for (WebElement option : options){
            if(option.getText().equals("Red") || option.getText().equals("Silver"))  {
                option.click();
            }
        }

        Select select = new Select(driver.findElement(By.name("color")));
        int selectSize = select.getAllSelectedOptions().size();

        assertEquals(selectSize, 2);
        for (WebElement option : select.getAllSelectedOptions()) {
            act_options.add(option.getText());
        }

        assertEquals(act_options.toArray(), exp_options.toArray());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

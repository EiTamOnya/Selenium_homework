package Homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

public class HomeworkLecture5 {
    static WebDriver driver;

    String restaurantName = "Китайски ресторант Сингуейбинлоу";

    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\drivers\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void findRestaurant() {
        driver.get("https://www.foodpanda.bg/");
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.findElement(By.id("delivery-information-postal-index")).sendKeys("15 булевард „Шипченски проход София");
        driver.findElement(By.cssSelector("button[data-testid='delivery_button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("restaurants-search-input")));
        driver.findElement(By.className("restaurants-search-input")).sendKeys(restaurantName);
        driver.findElement(By.className("restaurants-search-input")).click();
        driver.findElement(By.cssSelector("button[data-testid='toolbox-search-anyway']")).click();

        wait.until(new ExpectedCondition<Boolean>(){
            public Boolean apply (WebDriver d){
                return d.findElement(By.cssSelector(".vendor-info span.name")).isDisplayed();
            }
        });

        List<WebElement> restaurants = driver.findElements(By.cssSelector(".vendor-info span.name"));
        for(WebElement restaurant : restaurants){
            if (restaurant.getText().equals(restaurantName)){
                restaurant.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vendor-info-main-headline")));
        assertEquals(driver.findElement(By.className("vendor-info-main-headline")).getText(), restaurantName);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}

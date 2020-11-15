package Homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Methods {
    
    public static void logIn(String username, String password, WebDriver driver) {
        driver.findElement(By.id("input-username")).sendKeys(username);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.cssSelector(".btn[type='submit']")).click();
    }
}

package FinalExamPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Browser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;

public class Register {
    private static final By LOC_INPUT_FIRSTNAME = By.id("input-firstname");
    private static final By LOC_INPUT_LASTNAME = By.id("input-lastname");
    private static final By LOC_INPUT_MAIL = By.id("input-email");
    private static final By LOC_INPUT_PHONE = By.id("input-telephone");
    private static final By LOC_INPUT_PASS = By.id("input-password");
    private static final By LOC_INPUT_CONFIRM = By.id("input-confirm");
    private static final By LOC_RADIO_YES = By.cssSelector("input[name='newsletter'][value='1']");
    private static final By LOC_CHECKBOX = By.cssSelector("input[type='checkbox']");
    private static final By LOC_BTN_CONTINUE = By.cssSelector("input.btn[value='Continue']");
    private static final By LOC_FORM_NAME = By.cssSelector("#content h1");
    private static final By LOC_REVIEW_ALERT = By.cssSelector(".alert");
    private static final By LOC_FAILED_INPUT = By.cssSelector(".text-danger");

    /**
     * Method checks if this is the correct page
     *
     * @param expectedTitle the expected title of the form which will be compared to the actual one
     * **/
    public static void checksPageTitle(String expectedTitle){
        String actualTitle = Browser.driver.findElement(LOC_FORM_NAME).getText();
        assertTrue(actualTitle.contains(expectedTitle));
    }

    /**
     * Method fills in the register form, while asserting the elements are correct
     * and registers the user
     *
     * @param firstName  the name with which to register
     * @param lastName  the surname with which to register
     * @param mail  the mail with which to register
     * @param phone  the phone with which to register
     * @param password  the password with which to register and confirm
     * @param subscribe  the value should be true for yes and false for no
     */
    public static void fillRegisterForm(String firstName, String lastName,
                                 String mail, String phone, String password, boolean subscribe) {

        WebElement firstNameEle = Browser.driver.findElement(LOC_INPUT_FIRSTNAME);
        assertEquals(firstNameEle.getAttribute("placeholder"), "First Name");
        firstNameEle.sendKeys(firstName);

        WebElement lastNameEle = Browser.driver.findElement(LOC_INPUT_LASTNAME);
        assertEquals(lastNameEle.getAttribute("placeholder"), "Last Name");
        lastNameEle.sendKeys(lastName);

        WebElement mailEle = Browser.driver.findElement(LOC_INPUT_MAIL);
        assertEquals(mailEle.getAttribute("placeholder"), "E-Mail");
        mailEle.sendKeys(mail);

        WebElement phoneEle = Browser.driver.findElement(LOC_INPUT_PHONE);
        assertEquals(phoneEle.getAttribute("placeholder"), "Telephone");
        phoneEle.sendKeys(phone);

        WebElement passEle = Browser.driver.findElement(LOC_INPUT_PASS);
        assertEquals(passEle.getAttribute("placeholder"), "Password");
        passEle.sendKeys(password);

        WebElement passConfirmEle = Browser.driver.findElement(LOC_INPUT_CONFIRM);
        assertEquals(passConfirmEle.getAttribute("placeholder"), "Password Confirm");
        passConfirmEle.sendKeys(password);

        if (subscribe){
            WebElement radioYes = Browser.driver.findElement(LOC_RADIO_YES);
            assertEquals(radioYes.findElement(By.xpath("./..")).getText(), "Yes");
            radioYes.click();
        }
        WebElement checkbox = Browser.driver.findElement(LOC_CHECKBOX);
        assertTrue(checkbox.findElement(By.xpath("./..")).getText().contains("Privacy Policy"));
        checkbox.click();

        WebElement continueBTN = Browser.driver.findElement(LOC_BTN_CONTINUE);
        assertEquals(continueBTN.getAttribute("value"), "Continue");
        continueBTN.click();
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

    /**
     * Method generates a random e-mail
     *
     *
     * @param length the length of the e-mail name
     */
    public static String randomMail(int length) {
        String lettersNumbers = "abcdefghijklmnopqrstuvxyz1234567890";

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int)(lettersNumbers.length() * Math.random());
            sb.append(lettersNumbers.charAt(index));
        }
        return sb.toString() + "@validmail.int";
    }

    public static void checkInputFields(){
        List<WebElement> failedInputs = Browser.driver.findElements(LOC_FAILED_INPUT);
        List<String> expectedText = Arrays.asList("First Name must be between 1 and 32 characters!",
        "Last Name must be between 1 and 32 characters!","E-Mail Address does not appear to be valid!",
        "Telephone must be between 3 and 32 characters!","Password must be between 4 and 20 characters!");

        List<String> actualText = new ArrayList<String>();

        for (WebElement input : failedInputs){
            actualText.add(input.getText());
        }
        assertEquals(actualText.toArray(), expectedText.toArray());
    }
}

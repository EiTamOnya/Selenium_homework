package FinalExam;

import FinalExamPages.Homepage;
import FinalExamPages.Register;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Browser;



public class FinalExam {
    private static final String firstName = "Ivan";
    private static final String lastName = "Petrov";
    private static final String newMail = Register.randomMail(10);
    private static final String usedMail = "petrovi@validmail.int";
    private static final String phone = "0888123456";
    private static final String password = "123456789";
    private static final String registerPage = "Register Account";
    private static final String successPage = "Your Account Has Been Created!";
    private static final String warningMessage = "Warning: E-Mail Address is already registered!";


    @BeforeClass
    public void setUp() {
        Browser.open("firefox");
    }

    @Test
    public void registerAccountPositive() {
        Homepage.goTo();
        Homepage.goToRegisterPage();
        Register.checksPageTitle(registerPage);
        Register.fillRegisterForm(firstName,lastName,newMail,phone,password,true);
        Register.checksPageTitle(successPage);
    }

    @Test
    public void registerAccountNegative() {
        Homepage.goTo();
        Homepage.goToRegisterPage();
        Register.checksPageTitle(registerPage);
        Register.fillRegisterForm("","","","","",true);
        Register.checksPageTitle(registerPage);
        Register.checkInputFields();
    }

    @AfterClass
    public void tearDown() {
        Browser.quit();
    }
}

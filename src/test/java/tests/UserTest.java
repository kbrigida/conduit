package tests;

import com.sun.org.glassfish.gmbal.Description;
import core.SeleniumSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.SignUpPage;
import java.util.Properties;

public class UserTest extends SeleniumSetUp {
    private WebDriver driver;
    private Properties prop;
    private String userName;
    private String userEmail;
    private String userPassword;

    @BeforeTest
    public void startTest() {
        prop = getMainProperties();
        driver = setUpDriver(prop.getProperty("test.conduitUrl"));

        String randomUserCode = String.valueOf(System.currentTimeMillis());
        userName = prop.getProperty("test.user").replace("*",randomUserCode);
        userEmail = prop.getProperty("test.user_email").replace("*",randomUserCode);
        userPassword = prop.getProperty("test.user_password").replace("*",randomUserCode);
    }

    @Test
    @Description("Create an account using the [Sign Up] link (manually). Check that new user can log in")
    public void createANewAccount() {
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        //Open page
        mainPage.waitForPageLoading();

        //Pass sign up process
        signUpPage.createANewUser(userName, userPassword,userEmail);

        //Login by new user
        loginPage.loginByUser(userEmail, userPassword);

        Assert.assertTrue(mainPage.checkThatUserLinkVisible(userName));
    }

    @AfterTest
    public void closeDriver() {
        quitDriver();
    }
}

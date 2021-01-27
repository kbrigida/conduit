package pages;

import core.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePageObject {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    //Web element initializing
    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signUpMainPageButton;
    @FindBy(xpath = "//*[@placeholder='Username']")
    private WebElement usernameSignUpField;
    @FindBy(xpath = "//*[@placeholder='Email']")
    private WebElement emailSignUpField;
    @FindBy(xpath = "//*[@placeholder='Password']")
    private WebElement passwordSignUpField;
    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpFinishRegistration;

    //Page methods
    public void createANewUser(String userName, String userPassword, String userEmail) {
        clickButton(signUpMainPageButton);
        fillField(userName, usernameSignUpField);
        fillField(userEmail, emailSignUpField);
        fillField(userPassword, passwordSignUpField);
        clickButton(signUpFinishRegistration);
    }


}
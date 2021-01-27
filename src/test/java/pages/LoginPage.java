package pages;

import core.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Sign in']")
    private WebElement signInMainPageButton;
    @FindBy(xpath = "//*[@placeholder='Email']")
    private WebElement emailSignInField;
    @FindBy(xpath = "//*[@placeholder='Password']")
    private WebElement passwordSignInField;
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signUpFinishRegistration;

    public void loginByUser(String userEmail, String password) {
        clickButton(signInMainPageButton);
        fillField(userEmail, emailSignInField);
        fillField(password, passwordSignInField);
        clickButton(signInMainPageButton);
    }


}

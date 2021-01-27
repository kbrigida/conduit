package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePageObject {
    WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButton(WebElement elem) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(elem));
        elem.click();
    }

    public void fillField(String fieldValue, WebElement elem) {
        elem.clear();
        elem.sendKeys(fieldValue);
    }

    public void waitForVisible(WebElement element) {
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForButtonClickable(WebElement element) {
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Integer getCountOfElementsOnPage(List<WebElement> elementList){
        return elementList.size();
    }

}

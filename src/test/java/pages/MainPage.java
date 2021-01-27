package pages;

import core.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePageObject {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "page-item")
    public WebElement pageLink;

    @FindBy(className = "article-preview")
    public List<WebElement> articleElements;

    @FindBy(xpath = "//*[contains(@class,'page-item')]")
    public List<WebElement> paginationLinks;

    @FindBy(xpath = "//*[@class='tag-default tag-pill'][text()='test']")
    public WebElement tagTest;

    @FindBy(xpath = "//*[@class='article-preview']//*[contains(text(),'test')]")
    public WebElement articleWithTestWord;


    public void waitForPageLoading() {
        waitForButtonClickable(pageLink);
    }

    public boolean checkThatOnly10ArticlesVisible() {
        return getCountOfElementsOnPage(articleElements) == 10 ? true : false;
    }

    public boolean checkThatOnly50PaginationLinksDisplayed() {
        return getCountOfElementsOnPage(paginationLinks) == 50 ? true : false;
    }

    public boolean checkTagTestExist(){
        return tagTest.isDisplayed();
    }

    public void clickOnTagAndCheckThatCorrectArticleVisible(){
        clickButton(tagTest);
        waitForPageLoading();
        waitForVisible(articleWithTestWord);
    }

    public boolean checkThatUserLinkVisible(String userName) {
        //Wait for load page before check user links. Tags should be display
        waitForPageLoading();
        return driver.findElement(By.xpath("//*[text()='" + userName + "']")).isDisplayed();
    }
}

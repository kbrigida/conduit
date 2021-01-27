package tests;

import com.sun.org.glassfish.gmbal.Description;
import core.SeleniumSetUp;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpForward;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

import java.util.Properties;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpForward.forward;
import static org.mockserver.model.HttpRequest.request;

public class ArticlesTest extends SeleniumSetUp {

        private WebDriver driver;
        private Properties prop;
        private MainPage mainPage;
        private ClientAndServer mockServer;

        @BeforeTest
        public void startTest() {
            prop = getMainProperties();
            driver = setUpDriver(prop.getProperty("test.conduitUrl"));
            mainPage = new MainPage(driver);
                mockServer = startClientAndServer(1080);

        }

        @Test(priority = 1)
        @Description("Assert that there are [10] articles per page and [50] pages in total")
        public void checkArticlesAndPagination() {
            //Open page
            mainPage.waitForPageLoading();

            //Check that 10 articles displayed
            Assert.assertTrue(mainPage.checkThatOnly10ArticlesVisible());

            //Check that 50 pagination links displayed
            Assert.assertTrue(mainPage.checkThatOnly50PaginationLinksDisplayed());
        }

        @Test(priority = 2)
        @Description("Assert that [test] tag exists and click it")
        public void checkTagTest() {
            //Open page
            mainPage.waitForPageLoading();

            //Check 'test' tag exist
            Assert.assertTrue(mainPage.checkTagTestExist());

            //Click on tag test. Wait for visibility articles with this tag
            mainPage.clickOnTagAndCheckThatCorrectArticleVisible();
        }

        @AfterTest
        public void closeDriver() {
            quitDriver();
        }
    }


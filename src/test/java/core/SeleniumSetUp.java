package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.proxy.LegacyProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpRequest;
import net.lightbody.bmp.proxy.http.RequestInterceptor;
import net.lightbody.bmp.proxy.http.ResponseInterceptor;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class SeleniumSetUp {
    private WebDriver driver;

    public WebDriver setUpDriver(String url) {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        String proxyOption = "--proxy-server" + seleniumProxy.getHttpProxy();
        options.addArguments(proxyOption);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public Properties getMainProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

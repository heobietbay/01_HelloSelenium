package khoa.selenium.pages;

import khoa.selenium.util.Browser;
import khoa.selenium.util.PropertyLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class TestBase {

    @BeforeClass
    public void init() {
        // please configure webdriver.chrome.driver in application.properties, find the chrome driver at https://sites.google.com/a/chromium.org/chromedriver/
        System.setProperty("webdriver.chrome.driver", PropertyLoader.loadProperty("webdriver.chrome.driver"));

        websiteUrl = PropertyLoader.loadProperty("site.url");
        webDriver = new ChromeDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterMethod
    public void setScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                WebDriver returned = new Augmenter().augment(webDriver);
                if (returned != null) {
                    File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
                    try {
                        FileUtils.copyFile(f,
                                new File(SCREENSHOT_FOLDER + result.getName() + SCREENSHOT_FORMAT));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ScreenshotException se) {
                se.printStackTrace();
            }
        }
    }
    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";

    protected WebDriver webDriver;

    protected String gridHubUrl;

    protected String websiteUrl;

    protected Browser browser;
}

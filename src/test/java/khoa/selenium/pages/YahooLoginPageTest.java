package khoa.selenium.pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class YahooLoginPageTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        // need this in order to manipulate Chrome, find the chrome driver at https://sites.google.com/a/chromium.org/chromedriver/
        System.setProperty("webdriver.chrome.driver","D:\\Workspace\\SELENIUM\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        baseUrl = "https://login.yahoo.com";
    }

    @Test
    public void testCwebLoginPage() throws Exception {
        driver.get(baseUrl);
        assertEquals(driver.getTitle(), "Yahoo - login");
        setScreenshot("1_loginPage");

        WebElement usernameTxt = getElement( () -> By.id("login-username") );
        usernameTxt.sendKeys("heobietbay11");

        // For yahoo page login, this button will be reloaded, so cannot hold one reference for it, have to search and fire each time
        getElement( () -> By.id("login-signin") ).click();

        WebElement passwdTxt = getElement( () -> By.id("login-passwd") );
        passwdTxt.sendKeys("djfkldsfkljasdlfkj");

        getElement( () -> By.id("login-signin") ).click();
        try {
            WebElement errorMsgEl = getElement( () -> By.id("mbr-login-error") );
            setScreenshot("2_errorMessage");
            assertEquals(errorMsgEl.getText(), "Invalid password. Please try again.");
        }
        catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     * Get an element, with a time out of 10 seconds. This is using explicit wait.
     * Only return an element if its present in the page, and its width, height greater than 0.
     * @param by a predicate, cound be find by id, or find by class name,....
     * @return the element, or an exception.
     */
    private WebElement getElement(Supplier<By> by) {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by.get()));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        assert driver != null;
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    private void setScreenshot(String name) {
        try {
            WebDriver returned = new Augmenter().augment(driver);
            if (returned != null) {
                File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(f,
                            new File(SCREENSHOT_FOLDER + name + "_" + System.currentTimeMillis() + SCREENSHOT_FORMAT));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (ScreenshotException se) {
            se.printStackTrace();
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";
}
package khoa.selenium.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * Created by Administrator on 12/9/2016.
 */
public class DriverUtil {

    /**
     * Get an element, with a time out of 10 seconds. This is using explicit wait.
     * Only return an element if its present in the page, and its width, height greater than 0.
     * @param driver the driver to interact with the browser.
     * @param timeOutInSeconds how long should we wait for the element.
     * @param by a predicate, cound be find by id, or find by class name,....
     * @return the element, or an exception.
     */
    public static WebElement getElement(WebDriver driver, int timeOutInSeconds,Supplier<By> by) {
        timeOutInSeconds = timeOutInSeconds < 1 ? 1 : timeOutInSeconds;
        return (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by.get()));
    }

    /**
     * Take screenshot, and save to (fullScreenShotName + screenShotFormat)
     * @param driver
     * @param fullScreenShotName the full name include the folder structure.
     * @param screenShotFormat png or jpeg.
     */
    public static void takeScreenshot(WebDriver driver,String fullScreenShotName,String screenShotFormat) {
        try {
            WebDriver returned = new Augmenter().augment(driver);
            if (returned != null) {
                File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(f,
                            new File(fullScreenShotName + "_" + System.currentTimeMillis() + screenShotFormat));
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
}

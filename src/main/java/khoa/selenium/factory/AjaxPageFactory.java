package khoa.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This is extended from PageFactory, in order to override locate element process.
 * With this factory, element will be considered 'usable' if it is displayed in the page.
 * {@link khoa.selenium.locator.CustomAjaxElementLocator#isElementUsable(WebElement)}
 */
public class AjaxPageFactory extends PageFactory {

    public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy) {
        T page = instantiatePage(driver, pageClassToProxy);
        initElements(driver, page);
        return page;
    }
    /**
     * Use the custom {@link khoa.selenium.factory.CustomAjaxElementLocatorFactory} to locate element.
     * @param driver The driver that will be used to look up the elements
     * @param page   The object with WebElement and List&lt;WebElement&gt; fields that
     *               should be proxied.
     */
    public static void initElements(WebDriver driver, Object page) {
        final WebDriver driverRef = driver;
        initElements(new CustomAjaxElementLocatorFactory(driverRef, TIME_OUT_IN_SECONDS), page);
    }

    /**
     * Direct copied from PageFactory.
     * @param driver
     * @param pageClassToProxy
     * @param <T>
     * @return
     */
    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public static final int TIME_OUT_IN_SECONDS = 5;
}

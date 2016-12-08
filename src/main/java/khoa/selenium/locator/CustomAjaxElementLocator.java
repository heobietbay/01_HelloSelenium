package khoa.selenium.locator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;

import java.lang.reflect.Field;

/**
 * This locator only consider an element usable if it is displayed.
 */
public class CustomAjaxElementLocator extends org.openqa.selenium.support.pagefactory.AjaxElementLocator {

    @Override
    protected boolean isElementUsable(WebElement element) {
        return element.isDisplayed();
    }

    public CustomAjaxElementLocator(SearchContext searchContext, Field field, int timeOutInSeconds) {
        super(searchContext, field, timeOutInSeconds);
    }
}

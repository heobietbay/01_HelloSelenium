package khoa.selenium.factory;

import khoa.selenium.locator.CustomAjaxElementLocator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * This factory will produce {@link CustomAjaxElementLocator}.
 */
public class CustomAjaxElementLocatorFactory implements ElementLocatorFactory {

    public ElementLocator createLocator(Field field) {
        return new CustomAjaxElementLocator(searchContext, field, timeOutInSeconds);
    }

    public CustomAjaxElementLocatorFactory(SearchContext searchContext, int timeOutInSeconds) {
        this.searchContext = searchContext;
        this.timeOutInSeconds = timeOutInSeconds;
    }
    private final SearchContext searchContext;
    private final int timeOutInSeconds;
}

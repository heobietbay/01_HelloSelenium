package khoa.selenium;

import khoa.selenium.util.PropertyLoader;

/**
 * Created by Administrator on 12/9/2016.
 */
public enum Configuration {
    INSTANCE;

    public int getTimeOutForElement()
    {
        return Integer.valueOf(PropertyLoader.loadProperty("timeout.element")) ;
    }

    public String chromeDriverLocation()
    {
        return PropertyLoader.loadProperty("webdriver.chrome.driver");
    }

    public String getSiteUrl()
    {
        return PropertyLoader.loadProperty("site.url");
    }
}

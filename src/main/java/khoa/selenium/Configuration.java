package khoa.selenium;

import khoa.selenium.util.PropertyLoader;

/**
 * Created by Administrator on 12/9/2016.
 */
public enum Configuration {
    INSTANCE;

    public int getTimeOutForElement()
    {
        return Integer.valueOf(PropertyLoader.INSTANCE.loadProperty("timeout.element")) ;
    }

    public String chromeDriverLocation()
    {
        return PropertyLoader.INSTANCE.loadProperty("webdriver.chrome.driver");
    }

    public String getSiteUrl()
    {
        return PropertyLoader.INSTANCE.loadProperty("site.url");
    }
}

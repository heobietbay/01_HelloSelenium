package khoa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by khoa on 12/4/2016.
 * For yahoo page login, since this page reloads each time Next button is clicked, better not cache element.
 */
public class YahooLoginPageV2 extends Page {

    public WebElement getLoginUsernameTxt() {
        return loginUsernameTxt;
    }

    public WebElement getLoginPasswdTxt() {
        return loginPasswdTxt;
    }

    public WebElement getLoginSigninBtn() {
        return loginSigninBtn;
    }

    public WebElement getLoginErrMsg() {
        return loginErrMsg;
    }

    public YahooLoginPageV2(WebDriver driver) {
        super(driver);
    }

    public static final String URL_LOGIN_YAHOO_COM = "https://login.yahoo.com";

    @FindBy(how = How.ID, id = "login-username")
    private WebElement loginUsernameTxt;

    @FindBy(how = How.ID, id = "login-passwd")
    public WebElement loginPasswdTxt;

    @FindBy(how = How.ID, id = "login-signin")
    private WebElement loginSigninBtn;

    @FindBy(how = How.ID, id = "mbr-login-error")
    private WebElement loginErrMsg;
}

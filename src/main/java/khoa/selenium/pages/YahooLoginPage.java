package khoa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by khoa on 12/4/2016.
 * For yahoo page login, since this page reloads each time Next button is clicked, better not cache element.
 */
public class YahooLoginPage extends Page {

    public WebElement getLoginUsernameTxt() {
        return loginUsernameTxt;
    }

    public WebElement getLoginPasswdTxt() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated( By.id("login-passwd")) ) ;
    }

    public WebElement getLoginSigninBtn() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated( By.id("login-signin")) ) ;
    }

    public WebElement getLoginErrMsg() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated( By.id("mbr-login-error")) ) ;
    }

    public YahooLoginPage(WebDriver driver) {
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

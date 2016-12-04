package khoa.selenium.pages;

import khoa.selenium.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*
 * Sample page
 * 
 * @author Sebastiano Armeli-Battana
 */
public class HomePage extends Page {

	public String getH1() {
		return h1Element.getText();
	}

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.TAG_NAME, using = Constants.H1_TAG)
	@CacheLookup
	private WebElement h1Element;

}

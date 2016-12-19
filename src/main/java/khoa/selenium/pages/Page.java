package khoa.selenium.pages;

import org.openqa.selenium.WebDriver;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 * 
 * @author Sebastiano Armeli-Battana
 */
public abstract class Page {

	WebDriver driver;
	public Page(WebDriver driver) {
		this.driver = driver;
	}
}

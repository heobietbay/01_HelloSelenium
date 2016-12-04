package khoa.selenium.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

	HomePage homepage;

	/**
	 * Run this init, with an optional path
	 * @param path path. If run with Intellij, please help configure under Run->Edit Configuration->TestNG->Parameters tab
	 */
	@Parameters({ "path" })
	@BeforeClass
	public void testInit(@Optional String path) {

		// Load the page in the browser
		webDriver.get(websiteUrl);
		homepage = PageFactory.initElements(webDriver, HomePage.class);
	}

	@Test
	public void testH1Existing() throws InterruptedException {
		Assert.assertTrue(homepage.getH1() != null);
	}

	@Test
	public void test2() throws InterruptedException {
		Assert.assertTrue(true);
	}
}

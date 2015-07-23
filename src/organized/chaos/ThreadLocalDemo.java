package organized.chaos;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static organized.chaos.PrintUtil.tPrint;

import java.net.MalformedURLException;
import java.util.Random;

public class ThreadLocalDemo {
	
	private static final int SLEEP_MIN = 2;
	private static final int SLEEP_MAX = 6;
	
	@BeforeMethod
	@Parameters({"os", "browserName"})
	public void beforeMethod(String os, String browserName) {

        boolean runLocal = false;

        WebDriver driver = null;
        
        if (runLocal) {
        	tPrint("Creating webDriver instance...");
        	driver = LocalDriverFactory.createInstance(browserName);
        } else {
        	tPrint("Creating RemoteWebDriver instance...");
			try {
				driver = RemoteDriverFactory.createInstance(os, browserName);
			} catch (MalformedURLException e) {
				tPrint("Issue creating remoteWebDriver: " + e.getMessage());
				e.printStackTrace();
			}
        }
		
        LocalDriverManager.setWebDriver(driver);
	}
	
	@Test
	public void testMethod1() {
		invokeBrowser("http://www.google.com");
		tPrint("Title: " + getTitle());
	}

	@Test
	public void testMethod2() {
		invokeBrowser("http://www.facebook.com");
		tPrint("Title: " + getTitle());
	}

	@Test
	public void testMethod3() {
		invokeBrowser("http://www.apple.com");
		tPrint("Title: " + getTitle());
	}

	@Test
	public void testMethod4() {
		invokeBrowser("http://www.twitter.com");
		tPrint("Title: " + getTitle());
	}

	@Test
	public void testMethod5() {
		invokeBrowser("http://www.bing.com");
		tPrint("Title: " + getTitle());
	}
	
	@Test
	public void testMethod6() {
		invokeBrowser("http://www.cnn.com");
		tPrint("Title: " + getTitle());
	}
	
	@Test
	public void testMethod7() {
		invokeBrowser("https://lifestore.aol.com");
		tPrint("Title: " + getTitle());
	}
	
	@AfterMethod
	public void afterMethod() {
		WebDriver driver = LocalDriverManager.getDriver();
        if (driver != null) {
        	tPrint("Killing webDriver instance...");
            driver.quit();
            tPrint("Webdriver instance is dead. HAHAHAHAHA!!!");
        }
	}

	
	private void invokeBrowser(String url) {
		tPrint("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
		tPrint("Loading url: " + url);
		LocalDriverManager.getDriver().get(url);
		sleep(getRandomInt(SLEEP_MIN, SLEEP_MAX));
	}
	
	private String getTitle() {
		return LocalDriverManager.getDriver().getTitle();
	}

	private static int getRandomInt(int low, int high) {
		Random r = new Random();
		return r.nextInt(high - low) + low;
	}

	private void sleep(int seconds) {
		try {
			tPrint("Sleeping " + seconds + " seconds...");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			tPrint("Sleep interrupted: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
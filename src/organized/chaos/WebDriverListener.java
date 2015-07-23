package organized.chaos;
 
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import static organized.chaos.PrintUtil.tPrint;

import java.net.MalformedURLException;

public class WebDriverListener implements IInvokedMethodListener {
 
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String os = method.getTestMethod().getXmlTest().getLocalParameters().get("os");
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            
            tPrint("Creating webDriver instance...");
//            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            WebDriver driver = null;
			try {
				driver = RemoteDriverFactory.createInstance(os, browserName);
			} catch (MalformedURLException e) {
				tPrint("Issue getting remoteWebDriver: " + e.getMessage());
				e.printStackTrace();
			}
            DriverManager.setWebDriver(driver);
        }
    }
 
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
            	tPrint("Killing webDriver instance...");
                driver.quit();
                tPrint("Webdriver instance is dead.HAHAHAHAHA!!!");
            }
        }
    }
}
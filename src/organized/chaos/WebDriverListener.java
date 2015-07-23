package organized.chaos;
 
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import static organized.chaos.PrintUtil.tPrint;

public class WebDriverListener implements IInvokedMethodListener {
 
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            tPrint("Creating webDriver instance...");
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            LocalDriverManager.setWebDriver(driver);
        }
    }
 
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = LocalDriverManager.getDriver();
            if (driver != null) {
            	tPrint("Killing webDriver instance...");
                driver.quit();
                tPrint("Webdriver instance is dead.HAHAHAHAHA!!!");
            }
        }
    }
}
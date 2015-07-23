package organized.chaos;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
 
class LocalDriverFactory {
	
    static WebDriver createInstance(String browserName) {
    	
        WebDriver driver = null;
        
        if (browserName.toLowerCase().contains("firefox")) {
            return new FirefoxDriver();
        }
        if (browserName.toLowerCase().contains("internet")) {
            return new InternetExplorerDriver();
        }
        if (browserName.toLowerCase().contains("chrome")) {
            return new ChromeDriver();
        }
        
        return driver;
    }
    
}
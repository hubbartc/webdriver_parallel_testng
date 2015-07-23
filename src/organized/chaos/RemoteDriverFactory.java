package organized.chaos;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static organized.chaos.PrintUtil.*;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * one interesting part to be noted here is that the class has 
 * been purposefully given only package visibility [ notice how 
 * the keyword “public” is missing from the class declaration ].
 */
class RemoteDriverFactory {
	
	static WebDriver createInstance(String os, String browserName) throws MalformedURLException {
		
		DesiredCapabilities capabilites = getBrowserType(browserName);
		
		setOS(os, capabilites);
		
		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capabilites);
	}
	
	protected static void setOS(String os, DesiredCapabilities capabilities) {
		if ("osx".equalsIgnoreCase(os) || "mac".equalsIgnoreCase(os)) {
			tPrint("Setting operating system to MAC");
			capabilities.setPlatform(Platform.MAC);
		} else if ("xp".equalsIgnoreCase(os)) {
			tPrint("Setting operating system to XP");
			capabilities.setPlatform(Platform.XP);
		} else if ("vista".equalsIgnoreCase(os)) {
			tPrint("Setting operating system to VISTA");
			capabilities.setPlatform(Platform.VISTA);
		} else if ("win7".equalsIgnoreCase(os)) {
			//TODO: see how to select WIN7 as that's what the grid has config'd
			tPrint("Setting operating system to VISTA because all 'VISTA' machines on grid are actually Win7 machines");
			capabilities.setPlatform(Platform.VISTA);
		} else if ("win8".equalsIgnoreCase(os)) {
			tPrint("Setting operating system to windows");
			capabilities.setPlatform(Platform.WIN8);
		} else if ("windows".equalsIgnoreCase(os)) {
			tPrint("Setting operating system to windows");
			capabilities.setPlatform(Platform.WINDOWS);
		} else {
			// if no platform is specified we go with whatever is available
			tPrint("Did not recognize operating system specified.  " + os);
			tPrint("Sending request to whichever platform is available");
		}
	}
	
	protected static DesiredCapabilities getBrowserType(String browserType) {
		
		if ("chrome".equalsIgnoreCase(browserType)) {
			tPrint("Setting browser to chrome");
			return  DesiredCapabilities.chrome();
		} else if ("firefox".equalsIgnoreCase(browserType)) {
			tPrint("Setting browser to firefox");
			return  DesiredCapabilities.firefox();
		} else if ("ie".equalsIgnoreCase(browserType)) {
			tPrint("Setting browser to Internet Explorer");
			return  DesiredCapabilities.internetExplorer();
		} else {
			tPrint("Did not recognize browser specified: " + browserType);
			tPrint("Defaulting to firefox browser");
			return  DesiredCapabilities.firefox();
		}
	}
	
}

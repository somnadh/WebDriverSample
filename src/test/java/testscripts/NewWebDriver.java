package testscripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class NewWebDriver {
	WebDriver driver;
	
  @Test
  public void test() throws MalformedURLException {
	  ChromeOptions options=new ChromeOptions();
	  options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
	  String strHub= "http://10.0.12.27:4444";
	  driver = new RemoteWebDriver(new URL(strHub),options);
	  driver.get("https://www.saucedemo.com/");
  }
}

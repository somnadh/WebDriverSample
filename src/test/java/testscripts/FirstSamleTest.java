package testscripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class FirstSamleTest {
	WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	@BeforeTest
	@Test
	  public void Setup() throws IOException {
		
		  String path= System.getProperty("user.dir")+"//src//test//resources//cofig.properties";
		  FileInputStream fi= new  FileInputStream(new File(path));
		  prop=new Properties();
		  prop.load(fi);
		  String strBrowser= prop.getProperty("browser");
		  
		  if (strBrowser.equalsIgnoreCase("chrome")){
			 driver=new ChromeDriver();
		  }
		  else {
			  driver=new EdgeDriver();
		  }
		  wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		  driver.manage().window().maximize();
		  driver.get(prop.getProperty("url"));
		  System.out.println("checking prop ");
		  Assert.assertEquals(driver.getTitle(), "Amazon");
		  driver.quit();
	  }
  @Test(groups="smoke test")
  public void TitleTest() {
	  ChromeDriver driver=new ChromeDriver();
	  driver.get("https://www.google.com/");
	  Assert.assertEquals(driver.getTitle(), "Google");
	  driver.quit();
  }
  
  @Test(groups="sanity test")
  public void TitleTestFalse() {
	  ChromeDriver driver=new ChromeDriver();
	  driver.get("https://www.google.com/");
	  Assert.assertEquals(driver.getTitle(), "Amazon");
	  driver.quit();
  }
  
  @Test
  public void Test4() {
	  //ChromeDriver driver=new ChromeDriver();
	  long id =Thread.currentThread().getId();
	  System.out.println("Test4 from testscripts==> "+id);
	 	  }
 
 
  }



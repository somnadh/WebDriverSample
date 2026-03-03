package Par;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Third {
	WebDriver driver;
	@Test
	  public void Test1() {
	  driver  =new EdgeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test1==> "+id);
		 	  }
	@Test
	  public void Test2() {
	  driver=new EdgeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test2==> "+id);
		 	  }
	@Test
	  public void Test3() {
	  driver=new EdgeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test3 from par==> "+id);
		 	  }
}

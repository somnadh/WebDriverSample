package Par;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SecondClass {
	ChromeDriver driver;
	@Test
	  public void Test1() {
	  driver  =new ChromeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test1==> "+id);
		 	  }
	@Test
	  public void Test2() {
	  driver=new ChromeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test2==> "+id);
		 	  }
	@Test
	  public void Test3() {
	  driver=new ChromeDriver();
		  long id =Thread.currentThread().getId();
		  System.out.println("Test3 from par==> "+id);
		 	  }
}

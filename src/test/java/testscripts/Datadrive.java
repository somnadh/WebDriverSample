package testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Datadrive {
	WebDriver driver;
@BeforeTest
public void setup() {
	driver=new ChromeDriver();
}

  /*@Test by passing one by one value E2xample 1
  public void LoginTest() {
	  driver=new ChromeDriver();
	  driver.get("https://www.saucedemo.com/");
	  WebElement uName=driver.findElement(By.id("user-name"));
	  uName.sendKeys("standard_user");
	  WebElement pwd=driver.findElement(By.id("password"));
	  pwd.sendKeys("secret_sauce");
	  WebElement btnLogin=driver.findElement(By.id("login-button"));
	  btnLogin.click();
	  WebElement header=driver.findElement(By.cssSelector("span.title"));
	  Assert.assertTrue(header.isDisplayed());
	  driver.close();
	  	  
  }
  @Test(dataProvider="loginData")
  //Data driver example 2
  public void LoginTest(String StrUser,String strPwd) {
	 
	  driver.get("https://www.saucedemo.com/");
	  WebElement uName=driver.findElement(By.id("user-name"));
	 // uName.sendKeys("standard_user");
	  uName.sendKeys(StrUser);
	  WebElement pwd=driver.findElement(By.id("password"));
	  //pwd.sendKeys("secret_sauce");
	  pwd.sendKeys(strPwd);
	  WebElement btnLogin=driver.findElement(By.id("login-button"));
	  btnLogin.click();
	 
	 // WebElement header=driver.findElement(By.cssSelector("span.title"));
	  //Assert.assertTrue(header.isDisplayed());
	 
	  	  
  } */

@Test(dataProvider="loginData")

//Data driver example 3
public void LoginTest(String StrUser,String strPwd) {
	 
	  driver.get("https://www.saucedemo.com/");
	  WebElement uName=driver.findElement(By.id(readObjPath("username")));
	 // uName.sendKeys("standard_user");
	  uName.sendKeys(StrUser);
	  WebElement pwd=driver.findElement(By.id(readObjPath("password")));
	  //pwd.sendKeys("secret_sauce");
	  pwd.sendKeys(strPwd);
	  WebElement btnLogin=driver.findElement(By.id(readObjPath("loginBtn")));
	  btnLogin.click();
	 
	 // WebElement header=driver.findElement(By.cssSelector("span.title"));
	  //Assert.assertTrue(header.isDisplayed());
	 
	  	  
}  

  @DataProvider(name="loginData")
  public Object[][] getData() throws CsvValidationException,IOException{
	  String path=System.getProperty("user.dir")+"//src//test//resources//testData/loginData.csv";
	  
	  CSVReader reader = new CSVReader(new FileReader(path));
	  
	  String cols[];
	  ArrayList<Object> dataList =new ArrayList<Object>();
	  while ((cols = reader.readNext())!=null) {
		  Object record[]= {cols[0],cols[1]};
		  dataList.add(record);
		  }
	  reader.close();
	  return dataList.toArray(new Object[dataList.size()][]);
	  }
  
  public String readObjPath(String objName) {
	  String objPath="";
	  String path1=System.getProperty("user.dir")+"//src//test//resources//testData/loginRepo.xlsx";
	  //HSSF->xls
	  //XSSF->xlsx
	  FileInputStream fin;
	  XSSFWorkbook workbook=null;
	  
	  try {
		  fin=new FileInputStream(path1);
		  workbook =new XSSFWorkbook(fin);
	  }catch(FileNotFoundException e) {
		  e.printStackTrace();
		  }catch(IOException e) {
		  e.printStackTrace();
		  }
	  XSSFSheet loginSheet =workbook.getSheet("LoginPage");
	  int numRows=loginSheet.getLastRowNum();
	  for(int i=1; i<= numRows;i++ ) {
		  XSSFRow row=loginSheet.getRow(i);
		  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
			  objPath=row.getCell(1).getStringCellValue();
		  }
	  }
		  
		  return objPath;
	  }
	  
		  
	  
	  
	  
  }
	  
  


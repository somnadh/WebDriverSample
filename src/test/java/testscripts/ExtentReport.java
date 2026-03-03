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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.util.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import CommonUtils.Utility;

public class ExtentReport {
	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
		@BeforeTest
		public void setup() {
			driver=new ChromeDriver();
		}
	
		  @BeforeTest
		  public void setupExtent() {
			  extentReports =new ExtentReports();
			  spark=new ExtentSparkReporter("test-output/SparkReport.html");
			  extentReports.attachReporter(spark);
		  }

			
@Test(dataProvider="loginData")
public void LoginTest(String StrUser,String strPwd) {
	 extentTest=extentReports.createTest("First Report");
	  driver.get("https://www.saucedemo.com/");
	  WebElement uName=driver.findElement(By.id(readObjPath("username")));
	 // uName.sendKeys("standard_user");
	  uName.sendKeys(StrUser);
	  WebElement pwd=driver.findElement(By.id(readObjPath("password")));
	  //pwd.sendKeys("secret_sauce");
	  pwd.sendKeys(strPwd);
	  WebElement btnLogin=driver.findElement(By.id(readObjPath("loginBtn")));
	  btnLogin.click();
	driver.findElement(By.cssSelector("span.title")).isDisplayed();
	 // Assert.assertTrue(header.isDisplayed());
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
	  
	@AfterTest
	public void finishExtenet() {
		extentReports.flush();
	}
	  
	@AfterMethod
	public void teardown(ITestResult result) {
		String path=Utility.getScreenshotpath(driver);
		if(result.getStatus() == result.FAILURE) {
			extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			//String path=Utility.getScreenshotpath(driver);
			extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else {
			extentTest.pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
	}
}
	  
  
	  
  


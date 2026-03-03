package apiTesting;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUpdateTest {
  @Test
  public void CreateBooking() throws InterruptedException 
  {
	Thread.sleep(4000);
	File jsonFile=new File("src//test//resources//testData//booking.json");
	RestAssured.baseURI="https://restful-booker.herokuapp.com";
	Response resp=RestAssured.given()
				  .accept("application/json")
				  .contentType("application/json")
				  .body(jsonFile)
				  .post("/booking");
	//System.out.println(resp.getStatusCode());
	Assert.assertEquals(resp.getStatusCode(), 200);
	System.out.println(resp.getBody().prettyPrint());
	JsonPath json1=resp.jsonPath();
	Assert.assertEquals(json1.get("booking.firstname"),"Somu");	
  }
  @Test
  public void UpdateBooking() throws InterruptedException 
  {
	String strToken="";
	File bookingFile=new File("src//test//resources//testData//booking.json");
	RestAssured.baseURI="https://restful-booker.herokuapp.com";
	File jsonFile=new File("src//test//resources//testData//user.json");
	Response resp=RestAssured.given()
				  .accept("application/json")
				  .contentType("application/json")
				  .body(jsonFile)
				  .post("/auth");
	resp.getBody().jsonPath().prettyPrint();
	strToken=resp.getBody().jsonPath().getString("token");
	System.out.println("Token Value===>  "+strToken);
	
	/*
	 * Response putResp=RestAssured.given() .accept("application/json")
	 * .contentType("application/json") .cookie("token",strToken) .body(bookingFile)
	 * .put("/booking/200"); System.out.println(putResp.getStatusCode());
	 * Assert.assertEquals(delResp.getStatusCode(), 200);
	 * System.out.println(putResp.getBody().prettyPrint());
	 * System.out.println("Token Value===>  "+strToken); JsonPath
	 * json2=putResp.jsonPath(); Assert.assertEquals(json2.get("firstname"),"Somu");
	 */
	
	Response delResp=RestAssured.given()
			  .contentType("application/json")
			  .cookie("token",strToken)
			  .body(bookingFile)
			  .put("/booking/701");
	System.out.println(delResp.getStatusCode());
	System.out.println(delResp.getStatusLine());
	Assert.assertEquals(delResp.getStatusCode(), 200);
	
  }
}

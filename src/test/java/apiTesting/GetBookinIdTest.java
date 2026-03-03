package apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetBookinIdTest {
  /**
 * 
 */
@Test
  public void getAllBookings() {
	  
	  RestAssured.baseURI="https://restful-booker.herokuapp.com";
	  Response response=RestAssured.get("/booking");
	  int statusCode=response.getStatusCode();
	  Assert.assertEquals(statusCode, 200);
	  Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	  ResponseBody body=response.getBody();
	  String bodyAsString=body.asString();
	  System.out.println(bodyAsString);
  }
@Test
public void getBookingDetails() throws InterruptedException {
	  Thread.sleep(3000);
	  RestAssured.baseURI="https://restful-booker.herokuapp.com";
	  Response response=RestAssured.get("/booking/2");
	  int statusCode=response.getStatusCode();
	  Assert.assertEquals(statusCode, 200);
	  Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	  ResponseBody body=response.getBody();
	  String bodyAsString=body.asString();
	  System.out.println(bodyAsString);
	  JsonPath jsonPath=response.jsonPath();
	  Assert.assertEquals(jsonPath.get("firstname"), "Sally");
	  Assert.assertEquals(jsonPath.get("additionalneeds"), "Breakfast");
}
//End point URL
@Test
public void getAllEndPoint() {
	  
	  RestAssured.baseURI="https://conduit-realworld-example-app.fly.dev";
	  Response response=RestAssured.get("/api/tags");
	  int statusCode=response.getStatusCode();
	  Assert.assertEquals(statusCode, 200);
	  Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	  ResponseBody body=response.getBody();
	  String bodyAsString=body.asString();
	  System.out.println(bodyAsString);
}
@Test
public void queryParm() {
	  
	  RestAssured.baseURI="https://petstore.swagger.io";
	  RequestSpecification req=RestAssured.given();
	  Response response=(Response) req.queryParam("status","Availble");
	  int statusCode=response.getStatusCode();
	  Assert.assertEquals(statusCode, 200);
	  Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	  ResponseBody body=response.getBody();
	  String bodyAsString=body.asString();
	  System.out.println(bodyAsString);
}
}

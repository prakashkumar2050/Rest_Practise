package firstPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

public class AddPlace {
	
	public static void main (String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.Addlocation())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		
		String placeid = js.getString("place_id");
		
	System.out.println(placeid);
	
	// Update Location
	String newAddress = "Bangalore India";
	 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	 
	 //Get Location
	String getplaceresponse = given().log().all().queryParam("key", "qaclick123")
	 .queryParam("place_id", placeid)
	 .when().get("/maps/api/place/get/json")
	 .then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1 = new JsonPath(getplaceresponse);
	String ActualAddress = js1.get("address");
	System.out.println(ActualAddress);
	
	Assert.assertEquals(ActualAddress, newAddress);
	 		
	}

}

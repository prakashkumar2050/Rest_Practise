package pojo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import pojo.AddLocationPojoClass;

public class SerializeTest {
	
	public static void main (String[] args) {
	
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	AddLocationPojoClass P = new AddLocationPojoClass();
	Location L = new Location();
	L.setLat(-38.383494);
	L.setLng(33.427362);
	P.setLocation(L);
	P.setAccuracy(50);
	P.setAddress("Rafiganj");
	P.setLanguage("Hindi");
	P.setName("Prakash");
	P.setPhone_number("+91 1234567890");
	P.setWebsite("http://google.com");
	
	List<String> myList = new ArrayList<String>();
	myList.add("Shoe_Shop");
	myList.add("Mall");
	myList.add("Multiplex");
	P.setTypes(myList);
	
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(P)
	.when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));

}
}

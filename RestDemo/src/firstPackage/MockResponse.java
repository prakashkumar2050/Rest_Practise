package firstPackage;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class MockResponse {
	public static void main (String[] args) {
	
	JsonPath js = new JsonPath(Payload.CoursePrice());
	
	//Return no. of courses returned by API
	
	int coursecount = js.getInt("courses.size()");
	System.out.println(coursecount);
	
	//Print first course name
	String FirstCourse = js.get("courses[0].title");
	System.out.println(FirstCourse);
	
	//Print Course Amount
	int PurchaseAmount = js.getInt("dashboard.PurchaseAmount");
	System.out.println(PurchaseAmount);
	
	//Print all course title and price
	for(int i = 0; i<coursecount; i++) {
		String CourseTitle = js.get("courses["+i+"].title");
		System.out.println(CourseTitle);
		System.out.println(js.get("courses["+i+"].Price").toString());
	}
	
	//Print no of courses sold by RPA
	System.out.println("Print no of courses sold by RPA");
	for(int i = 0; i<coursecount; i++) {
		String CourseTitle = js.get("courses["+i+"].title");
		if(CourseTitle.equalsIgnoreCase("RPA")) {
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			break;
		}
	}
	
	
}
}

package firstPackage;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	
	public static void main (String[] args) {
		int sum =0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for(int i =0;i<count;i++) {
			int Price = js.getInt("courses["+i+"].Price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = Price*copies;
			System.out.println(amount);
			sum = sum + amount;			
		}
		System.out.println(sum);
		int PurchaseAmount = js.getInt("dashboard.PurchaseAmount");
		System.out.println(PurchaseAmount);
		Assert.assertEquals(PurchaseAmount, sum);
		
	}
	
}

package firstPackage;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\PK00667043\\Desktop\\Selenium_Jar\\chromedriver_win32\\chromedriver");
		//WebDriver driver = new ChromeDriver();
		//driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		//driver.findElement(By.cssSelector("input[type='email']")).sendKeys("krprakash.0203");
		//driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		//Thread.sleep(3000);
		//driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Window@1");
		//driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		//Thread.sleep(3000);
		//String url = driver.getCurrentUrl();
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2FxgHhxUjGeQuIZHeoMbQvMHrpSrGvAJa93C09YaSX6EWeLWuwPJO0jVlpkpLuGl3xCfBJHQ1jshTFIenYLQ0TQoI&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none#";
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		
		String accesstoken = js.getString("access_token");
		
		String response = given().queryParam("access_token", accesstoken)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().log().all().extract().response().asString();
		JsonPath js1 = new JsonPath(accesstoken);
		
		System.out.println(response);
	}
}

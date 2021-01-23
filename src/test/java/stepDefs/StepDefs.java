package stepDefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.junit.Assert;
import configReaders.Utils;
import io.cucumber.java.en.Given;

public class StepDefs {
	public static String idOfNewUser;
	public  ArrayList<Integer> idOfPost = new ArrayList<>();
	public  ArrayList<String> emailList = new ArrayList<>();
	private Utils conf = new Utils();
	private String testlink = conf.readConfig("endpoint").toString();

	
//This method is to validate the users and fetch user id
	@Given("^I should get the id of the user \"([^\"]*)\"$")
	public void validateValidUserName(String name){
		String idOfUser =given()
				.get(testlink+"/users?username="+name)
				.then().assertThat().statusCode(200)
				.time(lessThan(10000L))
				.body("username",hasItem(name))
				.body("name", notNullValue())
				.body("address", notNullValue())
				.body("phone", notNullValue())
				.body("website", notNullValue())
				.body("company", notNullValue())
				.body("company.name", notNullValue())
				.body("company.catchPhrase", notNullValue())
				.body("company.bs", notNullValue())
				.body("geo.lat", notNullValue())
				.body("address.geo.lat", notNullValue())
				.body("address.geo.lng", notNullValue())
				.extract()
				.path("id").toString();
		idOfNewUser = idOfUser.replaceAll("[^\\d]", ""); 
		System.out.println("Id of the new user is: " + idOfNewUser);
	}

	//This method is to validate the invalid users
	@Given("^I should get null body when user \"([^\"]*)\" doesn't exist$")
	public void validateInvalidUserName(String name){
		given()
		.get(testlink+"/users?username="+name)
		.then().assertThat().statusCode(200)
		.time(lessThan(10000L))
		.body("body[0]",nullValue());
		System.out.println("Given User doesnt exits:" + name);
	}
}

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

	
//This method is to validate the users and fetch user ids
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

	//This method is to fetch/search posts 
	@Given("^I should get the posts written by the user$")
	public void searchForPosts(){
		int userIdValue = Integer.parseInt(idOfNewUser);
		idOfPost =given()
				.queryParam("userId", userIdValue)
				.get(testlink+"/posts/")
				.then().assertThat().statusCode(200)
				.time(lessThan(2000L))
				.body("userId[0]", notNullValue())
				.body("id[0]", notNullValue())
				.body("title[0]", notNullValue())
				.body("body[0]", notNullValue())
				.extract()
				.path("id");
		System.out.println("Id of the post is: " + idOfPost);
	}

	//This method is to validate no posts is returned for the invalid userid
	@Given("^I should not get the posts for invalid userid \"([^\"]*)\"$")
	public void searchPostByInvalidUserId(String userid){
		given()
		.queryParam("userId", userid)
		.get(testlink+"/posts/")
		.then().assertThat().statusCode(200)
		.time(lessThan(2000L))
		.body("body[0]",nullValue());
		System.out.println("Given UserID doesnt exits:" + userid);
	}

	//This method is to fetch comments and validate the email format
	@Given("^I should see the emails in proper format$")
	public void validateEmailFormat(){
		boolean isSuccess = true;
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		for(int i : idOfPost) {
			emailList =given()
					.pathParam("postId", i)
					.get(testlink+"/posts/{postId}/comments")
					.then().assertThat().statusCode(200)
					.time(lessThan(2000L))
					.body("postId[0]", notNullValue())
					.body("id[0]", notNullValue())
					.body("name[0]", notNullValue())
					.body("email[0]", notNullValue())
					.body("body[0]", notNullValue())
					.extract()
					.path("email");
			for(String email:emailList) {
				java.util.regex.Matcher matcher = pattern.matcher(email);
				System.out.println("The Email address is: " + email +" : "+ matcher.matches());
			}
			Assert.assertEquals(true, isSuccess);
		}
	}

	//This method is to validate no comments is returned for invalid postid
	@Given("^I should not get the comments for invalid postid \"([^\"]*)\"$")
	public void searchForPostByInvalidPostId(String postId){
		given()
		.pathParam("postId", postId)
		.get(testlink+"/posts/{postId}/comments")
		.then().assertThat().statusCode(200)
		.time(lessThan(2000L))
		.body("body[0]", nullValue());
		System.out.println("comments doesnt exist for invalid post id" + postId );
	}
}

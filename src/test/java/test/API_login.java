package test;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class API_login {
	public static String jsonid;
	public static String storyid;
	
	@Test(priority=1)
	public void logintoJira() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "     \"username\": \"rashmi\",\r\n"
				+ "      \"password\": \"rashmi\"\r\n"
				+ " }").contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().extract().response();
		
//		System.out.println(response.asString());
		
		System.out.println(response.getStatusCode());
		
		JSONObject jb=new JSONObject(response.asString());
		jsonid="JSESSIONID="+jb.getJSONObject("session").get("value").toString();
		
		System.out.println(jsonid);
		
	}

	@Test(priority=2)
	public void createUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "   \"fields\": {\r\n"
				+ "       \"project\": {\r\n"
				+ "           \"key\": \"AM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Create userstory on 6th Feb2024 with Rest\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "           \"name\": \"Story\"\r\n"
				+ "       }\r\n"
				+ " }\r\n"
				+ " }").contentType(ContentType.JSON).header("Cookie",jsonid)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		
//		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
		JSONObject jb=new JSONObject(response.asString());
		storyid=jb.get("key").toString();
		System.out.println(storyid);
			
	}	
	
	@Test(priority=3)
	public void getUserStory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",jsonid)
		.when().get("/rest/api/2/issue/"+ storyid )
		.then().log().body().extract().response();
		
//		System.out.println(response.asString());
		
		System.out.println(response.getStatusCode());
		
	}
	@Test(priority=4)
	public void updateUserStory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "   \"fields\": {\r\n"
				+ "       \"project\": {\r\n"
				+ "           \"key\": \"AM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Update userstory on 6th Feb2024 with Rest\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "           \"name\": \"Story\"\r\n"
				+ "       }\r\n"
				+ " }\r\n"
				+ " }").contentType(ContentType.JSON).header("Cookie",jsonid)
		.when().put("/rest/api/2/issue/"+ storyid )
		.then().log().body().extract().response();
		
//		System.out.println(response.asString());
		
		System.out.println(response.getStatusCode());
		
	}
	

}

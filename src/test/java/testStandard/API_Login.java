package testStandard;

import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Login {
	public static String jsonid;
	public static String storyid;
	
	@Test(priority=1)
	public void logintoJira() throws IOException, ParseException {
		String data=API_ReadFile.getdata("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\LoginReqbody.json");
		Response response=RestAssured.given().baseUri("http://localhost:9009").body(data).contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().extract().response();
	
		
		System.out.println(response.getStatusCode());
		
		JSONObject jb=new JSONObject(response.asString());
		jsonid="JSESSIONID="+jb.getJSONObject("session").get("value").toString();
		
		System.out.println(jsonid);
		
	}
	@Test(priority=2)
	public void createUserstory() throws IOException, ParseException {
		String data=API_ReadFile.getdata("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\Requestbody.json");
		
		Response response=RestAssured.given().baseUri("http://localhost:9009").body(data).contentType(ContentType.JSON).header("Cookie",jsonid)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		
//		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
		JSONObject jb=new JSONObject(response.asString());
		storyid=jb.get("key").toString();
		System.out.println(storyid);
			
	}	
	

}

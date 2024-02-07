package testAdvance;

import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testStandard.API_ReadFile;

public class API_Login {
	public static String jsonid;
	public static String storyid;
	
	@Test
	public void login() throws IOException, ParseException {
	String data=API_ReadData.fetchdata("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\LoginReqbody.json");
	
	String url=API_ReadfromProp.getproperty("URL");
//	System.out.println(url);
	
	Response response=RestAssured.given().baseUri(url).body(data).contentType(ContentType.JSON)
	.when().post("/rest/auth/1/session")
	.then().extract().response();
	
	JSONObject jb=new JSONObject(response.asString());
	jsonid="JSESSIONID="+jb.getJSONObject("session").get("value").toString();
	
	System.out.println(jsonid);
	
	}
	@Test(priority=2)
	public void createBug() throws IOException, ParseException {
		String data=API_ReadData.fetchdata("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\Requestbody.json");
		
		JSONObject updatejb=new JSONObject(data);
		updatejb.getJSONObject("fields").put("summary","create a bug with Rest").getJSONObject("issuetype").put("name", "Bug").toString();
		
		String url=API_ReadfromProp.getproperty("URL");
		
		Response response=RestAssured.given().baseUri(url).body(updatejb.toString()).contentType(ContentType.JSON).header("Cookie",jsonid)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
		JSONObject jb=new JSONObject(response.asString());
		storyid=jb.get("key").toString();
		System.out.println(storyid);
	
	

	}
}

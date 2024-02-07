package testAdvance;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class API_UpdateData {
	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fr=new FileReader("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\Requestbody.json");
		JSONParser jp=new JSONParser();
		String data=jp.parse(fr).toString()	;	
		
		JSONObject jb=new JSONObject(data);
		jb.getJSONObject("fields").put("summary","update userstory to new Story on 6th Feb").toString();
		
	
		//printing the updated result
		System.out.println(jb.toString());
	}

}

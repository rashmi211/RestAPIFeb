package testAdvance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class API_ReadData {
	public static String fetchdata(String path) throws IOException, ParseException {
		FileReader fr=new FileReader(path);
		JSONParser jp=new JSONParser();
		String data=jp.parse(fr).toString();
		return data;

	}

}

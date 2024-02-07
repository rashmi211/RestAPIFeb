package testAdvance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class API_ReadfromProp {
	public static String getproperty(String property) throws IOException {
		FileReader fr=new FileReader("D:\\ProjectOnGit\\APIAutomation\\OrgApi1\\src\\main\\java\\inputs\\port.properties");
		Properties prop=new Properties();
		prop.load(fr);
		String url=prop.getProperty(property);	
		return url;
	
		
	}

}

package utils;

import java.io.File;
import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

public class JsonReader {
	
	private File jsonFile;
	public String fileName;
	
	public JsonReader(String fileName){
		
		this.fileName = fileName;
	}
	
	
	public String getData(String locator) throws IOException {
		
		try {
			
			jsonFile = new File(fileName);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return JsonPath.read(jsonFile, "$."+locator);
				
		
	}

}

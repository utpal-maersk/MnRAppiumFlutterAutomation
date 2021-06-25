package utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Ahmer");
		
		File files = new File(System.getProperty("user.dir")+"/Apps/Maerskline/Android/ ");
		
		System.out.println(""+System.getProperty("user.dir")+"/Apps/Maerskline/Android/ ");
		
		// create object of Path
        Path path = Paths.get(System.getProperty("user.dir")+"/Apps/Maerskline/Android/ ");
  
        // call getFileName() and get FileName path object
        Path fileName = path.getFileName();
  
        // print FileName
        System.out.println("FileName: "
                           + fileName.toString());
	}

}

package fr.tbr.iamcore.tests.identity.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
	
	public static void main(String[] args){
		
		Properties properties = new Properties();
		File file = new File("configuration.properties");
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			properties.load(fileReader); 
			System.out.println(properties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	

		
		
	}

}

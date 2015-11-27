package fr.tbr.iam.configuration.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import fr.tbr.iam.configuration.IConfiguration;

public class FileBasedConfiguration implements IConfiguration{

	private static final String JDBC_USER_NAME = "JDBCUserName";
	private Properties properties;
	
	public FileBasedConfiguration() {
		this.properties =  new Properties();
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
	
	@Override
	public String getJDBCUserName() {
		return this.properties.getProperty(JDBC_USER_NAME);
	}

	@Override
	public String getJDBCPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJDBCConnectionString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJDBCDriverClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConfiguredDAOType() {
		return "";
		
	}

}

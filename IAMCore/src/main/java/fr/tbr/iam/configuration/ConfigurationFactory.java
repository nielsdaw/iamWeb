package fr.tbr.iam.configuration;

import fr.tbr.iam.configuration.impl.DefaultConfiguration;
import fr.tbr.iam.configuration.impl.FileBasedConfiguration;

public class ConfigurationFactory {

	public static IConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return new FileBasedConfiguration();
	}
	
	

}

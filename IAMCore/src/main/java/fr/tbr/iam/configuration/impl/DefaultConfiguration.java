package fr.tbr.iam.configuration.impl;

import fr.tbr.iam.configuration.IConfiguration;

public class DefaultConfiguration implements IConfiguration {

	
	
	
	@Override
	public String getJDBCUserName() {
		return "tom";
	}

	@Override
	public String getJDBCPassword() {
		// TODO Auto-generated method stub
		return "tom";
	}

	@Override
	public String getJDBCConnectionString() {
		// TODO Auto-generated method stub
		return "jdbc:derby://localhost:1527/DerbyTest;create=true";
	}

	@Override
	public String getJDBCDriverClass() {
		// TODO Auto-generated method stub
		return "org.apache.derby.jdbc.ClientDriver";
	}

	@Override
	public String getConfiguredDAOType() {
		return "XML";
		
	}

}

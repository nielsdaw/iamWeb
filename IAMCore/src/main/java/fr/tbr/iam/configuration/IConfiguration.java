package fr.tbr.iam.configuration;

public interface IConfiguration {
	
	public String getJDBCUserName();
	//TODO find a way to avoid that
	public String getJDBCPassword();
	
	public String getJDBCConnectionString();
	public String getJDBCDriverClass();
	public String getConfiguredDAOType();

}

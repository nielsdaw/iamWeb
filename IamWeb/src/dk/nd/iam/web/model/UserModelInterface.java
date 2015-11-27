package dk.nd.iam.web.model;

public interface UserModelInterface {
	
	public User getUser(String username, String password);
	
	public void createUser(String username, String password);
	
	public boolean usernameExist(String username);
	
}

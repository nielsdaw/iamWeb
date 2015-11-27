package dk.nd.iam.web.model;

public interface UserModelInterface {
	
	/**
	 * Get the given user from session if it exists
	 * 
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUser(String username, String password);
	
	/**
	 * Create a user in database
	 * 
	 * @param username
	 * @param password
	 */
	public void createUser(String username, String password);
	
	/**
	 * Check if username exist in database
	 * 
	 * @param username
	 * @return boolean true if exist, false otherwise
	 */
	public boolean usernameExist(String username);
	
}

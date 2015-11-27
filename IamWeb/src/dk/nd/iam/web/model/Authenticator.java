package dk.nd.iam.web.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticator {
	
	/*
	 * Username must start with a letter, and doesn't consist 
	 */
	public boolean isValidUsername(String name){
		if(name == null) return false;
		Pattern p = Pattern.compile("[A-z]+\\d*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	/*
	 * Password must be at least be of length 6 and consist of a capital letter and a special character
	 * @param password the password to validate
	 */
	public boolean isValidPassword(String password){
		Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[A-z])(?=.*[*'^¨~|;,.><!#¤%&()=?@£$€])(?=\\S+$).{6,}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(password);
		if(!m.matches()){return false;}
		
		return true;
	}
}

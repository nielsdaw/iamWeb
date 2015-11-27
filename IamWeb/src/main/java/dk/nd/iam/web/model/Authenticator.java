package dk.nd.iam.web.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticator {
	
	/*
	 * Username must start with a letter, but can consist of digits as well
	 * @param username String
	 */
	public boolean isValidUsername(String username){
		if(username == null) return false;
		Pattern p = Pattern.compile("[A-z]+\\d*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(username);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	/*
	 * Date must consist of 4 digits - 2 digits - 2 digits (could be a better solution)
	 * @param date the date to validate
	 */
	public boolean isValidName(String name){
		if(name == null) return false;
		Pattern p = Pattern.compile("[A-z]{1,20}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	/*
	 * Date must consist of 4 digits - 2 digits - 2 digits (could be a better solution)
	 * @param date the date to validate
	 */
	public boolean isValidBirthdate(String date){
		if(date == null) return false;
		Pattern p = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(date);
		if(!m.matches()){return false;}
		
		
		return true;
	}
	
	/*
	 * Password must be at least be of length 6 and consist of a capital letter and a special character
	 * @param password the password to validate
	 */
	public boolean isValidPassword(String password){
		if(password == null) return false;
		Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[A-z])(?=.*[*'^¨~|;,.><!#¤%&()=?@£$€])(?=\\S+$).{6,}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(password);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	/*
	 * Email must consist numbers, special characters a @ at least one letter/digit domain, and . 2-4 country (fr, dk, com) etc...
	 * @param email the email to validate
	 */
	public boolean isValidEmail(String email){
		if(email == null) return false;
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		if(!m.matches()){return false;}
		
		return true;
	}
	
}

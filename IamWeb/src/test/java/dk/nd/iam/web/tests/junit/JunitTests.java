package dk.nd.iam.web.tests.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dk.nd.iam.web.model.Authenticator;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@WebAppConfiguration
public class JunitTests {

	@Autowired
	IdentityDAOInterface dao;
	
	@Autowired
	Authenticator validator;
	
	
	@Test
	public void validUsername() {
		boolean test1 = validator.isValidUsername("Niels");
		boolean test2 = validator.isValidUsername("123");
		boolean test3 = validator.isValidUsername(null);
		boolean test4 = validator.isValidUsername("");
		boolean test5 = validator.isValidUsername("Niels123");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
		assertTrue(test5);
	}
	
	@Test
	public void validPassword() {
		boolean test1 = validator.isValidPassword("Password123!");
		boolean test2 = validator.isValidUsername("123");
		boolean test3 = validator.isValidUsername(null);
		boolean test4 = validator.isValidUsername("");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
	}
	
	
	@Test
	public void validFirsname() {
		boolean test1 = validator.isValidName("Niels");
		boolean test2 = validator.isValidName("123");
		boolean test3 = validator.isValidName(null);
		boolean test4 = validator.isValidName("");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
	}
	
	@Test
	public void validLastname() {
		boolean test1 = validator.isValidName("Dawartz");
		boolean test2 = validator.isValidName("123");
		boolean test3 = validator.isValidName(null);
		boolean test4 = validator.isValidName("");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
	}
	
	@Test
	public void validEmail() {
		boolean test1 = validator.isValidEmail("nielsdaw@gmail.com");
		boolean test2 = validator.isValidEmail("123");
		boolean test3 = validator.isValidEmail(null);
		boolean test4 = validator.isValidEmail("");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
	}
	
	@Test
	public void validBirthDate() {
		boolean test1 = validator.isValidBirthdate("1990-12-12");
		boolean test2 = validator.isValidBirthdate("123");
		boolean test3 = validator.isValidBirthdate(null);
		boolean test4 = validator.isValidBirthdate("");
		boolean test5 = validator.isValidBirthdate("12-12-1990");
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		assertFalse(test4);
		assertFalse(test5);
	}

}

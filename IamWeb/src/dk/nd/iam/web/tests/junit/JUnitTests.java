package dk.nd.iam.web.tests.junit;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import dk.nd.iam.web.model.User;
import dk.nd.iam.web.model.UserModelInterface;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
public class JUnitTests {

	@Autowired
	UserModelInterface userModel;
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	IdentityDAOInterface dao;
	
	@Test
	public void anHibernateTest(){
		Session session = this.factory.openSession();
		Assert.notNull(session);
		session.flush();
		session.close();
	}
	
	@Test
	public void createUser(){
		Session session = this.factory.openSession();
		Random rand = new Random();
		String username = "test" + rand;
		String password = "123";
		userModel.createUser(username, password);
		session.flush();
		session.close();
	}
	
	@Test
	public void userExistInDb(){
		Assert.isTrue(userModel.usernameExist("testUsername"));
	}
	
	@Test
	public void getUser(){
		Session session = factory.openSession();	// get session
		User result = (User) session.get(User.class, 1);	// get User with id 1
		System.out.println(result.toString());
		Assert.notNull(result);
		session.flush();
		session.close();
	}
	
	@Test
	public void authorizeUserLogin(){
		String username = "testUsername";
		String password = "TestPassword";
		User user = userModel.getUser(username, password);
		Assert.notNull(user);
		System.out.println(user);
	}
	
	@Test
	public void writeTestFromDao(){
		this.dao.write(new Identity("quentin", "decayeux", "qdc@qdc.com"));
	}
	
	@Test
	public void readTestFromDao(){
		List<Identity> searchResults = this.dao.search(new Identity("quentin", "decayeux", "qdc@qdc.com"));
		System.out.println(searchResults);
	}

}

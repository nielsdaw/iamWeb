package fr.tbr.iamcore.tests.identity.junit;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

@RunWith(SpringJUnit4ClassRunner.class)
//leading slash refers to the classpath root ie. the root is src/test/java
@ContextConfiguration(locations={"/applicationContext.xml"})
public class JUnitTest {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	IdentityDAOInterface dao;
	
	@Test
	public void anHibernateTest(){
		Session session = this.factory.openSession();
		session.close();
	}
	
	@Test
	public void writeTest(){
		Identity identity = new Identity("thomas", "broussard", "tbr@tbr.com");
		Identity identity2 = new Identity("quentin", "decayeux", "qdc@qdc.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse("1990-12-12");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(date);
		this.dao.write(identity);
		try {
			date = formatter.parse("1988-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity2.setBirthDate(date);
		this.dao.write(identity2);
	}
	
	@Test
	public void readAll(){
		List<Identity> result = this.dao.readAll();
		System.out.println("Read all result");
		for (Identity identity : result) {
			System.out.println(identity);
		}
		boolean test = result.size() > 0;
		Assert.isTrue(test);
	}
	
	@Test
	public void searchTest(){
		Identity identity = new Identity("", "", "qdc@qdc.com");
		List<Identity> result = this.dao.search(identity);
		System.out.println("Search Result");
		for (Identity identity2 : result) {
			System.out.println(identity2);
		}
		boolean test = result.size() > 0;
		Assert.isTrue(test);
	}
	
	@Test
	public void searchTest2(){
		Identity identity = new Identity("thomas", "", "");
		List<Identity> result = this.dao.search(identity);
		System.out.println("Search Result2");
		for (Identity identity2 : result) {
			System.out.println(identity2);
		}
		boolean test = result.size() > 0;
		Assert.isTrue(test);
	}
	
	@Test
	public void searchTest3(){
		Identity identity = new Identity("", "decayeux", "");
		List<Identity> result = this.dao.search(identity);
		System.out.println("Search Result3");
		for (Identity identity2 : result) {
			System.out.println(identity2);
		}
		boolean test = result.size() > 0;
		Assert.isTrue(test);
	}
	
	@Test
	public void emptySearch(){
		Identity identity = new Identity();
		System.out.println("empty: " + identity);
		List<Identity> result = this.dao.search(identity);
		boolean test = result.size() < 1;
		Assert.isTrue(test);
	}
	
	@Test
	public void updateTest(){
		Identity identity = new Identity("figaro", "decayeux", "qdc@qdc.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse("1988-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setId(1);
		identity.setBirthDate(date);
		dao.update(identity);
	}
	
	
	@Test
	public void deleteTest(){
		Identity identity = new Identity("figaro", "decayeux", "qdc@qdc.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse("1988-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setId(1);
		identity.setBirthDate(date);
		dao.delete(identity);
	}

}

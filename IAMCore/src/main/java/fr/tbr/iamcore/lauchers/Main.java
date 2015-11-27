/**
 * 
 */
package fr.tbr.iamcore.lauchers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.tbr.iam.configuration.IConfiguration;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

/**
 * @author Tom
 *
 */
public class Main {

	
	@Autowired
	DataSource ds;
	
	@Autowired
	IConfiguration conf;
	
	@Autowired
	IdentityDAOInterface dao;
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Main runner = applicationContext.getBean(Main.class);
		try{
			runner.selfCheck();
			runner.test();
		}catch(Exception e){
			System.out.println(e);
		}	
	}
	
	private void test() throws ParseException{
		Identity identity = new Identity("thomas", "broussard", "tbr@tbr.com");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = formatter.parse("2015/05/05");
		identity.setBirthDate(birthDate);
		dao.write(identity);
		List<Identity> list =  dao.readAll();	// test read
		System.out.println("Read:");
		for (Identity identity2 : list) {
			System.out.println(identity2);
		}
		
		Identity testSearch = list.get(0);	// test search
		List<Identity> search = dao.search(testSearch);
		System.out.println("Search expected 1 output");
		System.out.println(search.get(0));
		
		
		
		

	}
	
	
		
	private void run() throws ParseException {

		
		System.out.println("application started : " + ((conf == null)? "wrong configuration" : "good configuration"));
		System.out.println("JDBC user name" + conf.getJDBCUserName());
//		IdentityDAOInterface dao = IdentityDAOFactory.getDao();
		System.out.println("Welcome to the IAM system");


		
	}
	
	private void selfCheck() throws SQLException{
		ds.getConnection();
	}
	

}

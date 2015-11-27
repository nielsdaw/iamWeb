package dk.nd.iam.web.model;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserModelFunctions implements UserModelInterface{
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SessionFactory factory;
	
	
	@Override
	public User getUser(String username, String password) {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Session session = this.factory.openSession();
		Query query = session.createQuery("from User where USERNAME=:username and "
				+ "PASSWORD=:password");
		query.setString("username", username);
		query.setString("password", password);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}


	@Override
	public void createUser(String username, String password) {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		User user = new User(username, password);
		Session session = this.factory.openSession();
		session.saveOrUpdate(user);
		session.close();
	}


	@Override
	public boolean usernameExist(String username) {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Session session = this.factory.openSession();
		Query query = session.createQuery("from User where USERNAME=:username");
		query.setParameter("username", username);
		boolean result = query.uniqueResult() != null;
		session.close();
		return result;
	}
	
	
	

}

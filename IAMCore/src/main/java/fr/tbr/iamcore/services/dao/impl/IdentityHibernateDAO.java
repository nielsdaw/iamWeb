package fr.tbr.iamcore.services.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

/**
 * @author nillernoels
 *
 */
@Service
public class IdentityHibernateDAO implements IdentityDAOInterface  {
	
	final static String HQL_READ_ALL = "FROM Identity";
	final static String HQL_UPDATE = "UPDATE Identity SET FIRST_NAME = :firstName, LAST_NAME = :lastName, BIRTHDATE=:birthDate "
			+ "WHERE ID =:id";
	final static String HQL_DELETE = "DELETE Identity WHERE ID=:id";
	
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Identity> readAll() {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Session session = factory.openSession();	// get session
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(HQL_READ_ALL);	// get all Objects from db
		@SuppressWarnings("unchecked")
		List<Identity> list = (List<Identity>) query.list();
		session.flush();
		transaction.commit();
		session.close();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Identity> search(Identity identity) {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Identity> list = new ArrayList<Identity>();
		Session session = factory.openSession();	// get session
		Transaction transaction = session.beginTransaction();
		if(identity.getFirstName() == null && identity.getLastName() == null && identity.getEmail() == null);	// validate do nothing to list
		else if(identity.getFirstName() == "" && identity.getLastName() == "" && identity.getEmail() == "");	// same as above
		else{
			list = session.createCriteria(Identity.class).add(Example.create(identity).ignoreCase().enableLike(MatchMode.ANYWHERE)).list();	// search
		}
		session.flush();
		transaction.commit();
		session.close();
		return list;
	}

	@Override
	public void write(Identity identity) {
		try {
			ds.getConnection();		// check the database credentials
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(identity);
		session.flush();
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Identity identity) {
		Session session = factory.openSession();
		Query query = session.createQuery(HQL_UPDATE); 
		Transaction transaction = session.beginTransaction();
		query.setString("firstName", identity.getFirstName());
		query.setString("lastName", identity.getLastName());
		query.setDate("birthDate", identity.getBirthDate());
		query.setInteger("id", identity.getId());
		query.executeUpdate();
		session.flush();
		transaction.commit();
		session.close();
	}

	@Override
	public void delete(Identity identity) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(HQL_DELETE);
		query.setInteger("id", identity.getId());
		query.executeUpdate();
		session.flush();
		transaction.commit();
		session.close();
	}
	
	 
	
}
